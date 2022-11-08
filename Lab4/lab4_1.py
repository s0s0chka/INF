import re
import time

def findSubstringBetween(text, cr1, cr2):
    return text[text.find(cr1)+1:text.find(cr2, text.find(cr1)+1)]

def parseLine(line, currentParent, isStart, depth):
    resultLine = ""
    resultLine += '  ' * depth
    #Items
    if line.count(">") > 1:
        if isStart:
            resultLine += "- "
            isStart = False
        else:
            resultLine += "  "
        resultLine += findSubstringBetween(line, "<", ">") + ": " + findSubstringBetween(line, ">", "<")
    #Parents
    elif line.count(">") == 1 and "</" in line:
        isStart = True
        depth -= 1
        return ("", currentParent, isStart, depth)
    else:
        isStart = True
        depth += 1
        lineParent  = findSubstringBetween(line, "<",  ">")
        if lineParent == currentParent:
            return ("", currentParent, isStart, depth)
        else:
            currentParent = lineParent
        resultLine += lineParent + ":"
    resultLine += '\n'
    return (resultLine, currentParent, isStart, depth)

def parseLineRE(line, currentParent, isStart, depth):
    resultLine = ""
    resultLine += '  ' * depth
    #Items
    if len(re.findall(r"<", line)) > 1:
        if isStart:
            resultLine += "- "
            isStart = False
        else:
            resultLine += "  "
        resultLine += re.findall(r"<([^<>]*)>", line)[0] + ": " + re.findall(r">(.*)<", line)[0]
    #Parents
    elif len(re.findall(r"<", line)) == 1 and "</" in line:
        isStart = True
        depth -= 1
        return ("", currentParent, isStart, depth)
    else:
        isStart = True
        depth += 1
        lineParent  = re.findall(r"<(.*)>", line)[0]
        if lineParent == currentParent:
            return ("", currentParent, isStart, depth)
        else:
            currentParent = lineParent
        resultLine += lineParent + ":"
    resultLine += '\n'
    return (resultLine, currentParent, isStart, depth)

def ownXMLtoYAMLparse(inputFile, outputFile):
    lines = inputFile.readlines()
    currentParent = ""
    isStart = True
    depth = 0
    for line in lines:
        lineToWrite, currentParent, isStart, depth = parseLine(line, currentParent, isStart, depth)
        if lineToWrite != "":
            outputFile.write(lineToWrite)   
            
def ownXMLtoYAMLparseRE(inputFile, outputFile):
    lines = inputFile.readlines()
    currentParent = ""
    isStart = True
    depth = 0
    for line in lines:
        lineToWrite, currentParent, isStart, depth = parseLineRE(line, currentParent, isStart, depth)
        if lineToWrite != "":
            outputFile.write(lineToWrite)   
    

def importedXMLtoYAMLparse(inputFile, outputFile):
    import xmlplain
    root = xmlplain.xml_to_obj(inputFile, strip_space=True, fold_dict=True)
    xmlplain.obj_to_yaml(root, outputFile)

#
inputFilePath = './input.xml'
outputFilePath1 = './output1.yaml'
outputFilePath2 = './output2.yaml'
outputFilePath3 = './output3.yaml'

#Own parser without regex

ownTime = time.time()
for i in range(10):
    inputFile = open(inputFilePath, 'r', encoding='utf8')
    outputFile = open(outputFilePath1, 'w', encoding='utf8')
    ownXMLtoYAMLparse(inputFile, outputFile)
    inputFile.close()
    outputFile.close()
ownTime = time.time()-ownTime

#Own parser with regex

ownTimeRE = time.time()
for i in range(10):
    inputFile = open(inputFilePath, 'r', encoding='utf8')
    outputFile = open(outputFilePath2, 'w', encoding='utf8')
    ownXMLtoYAMLparseRE(inputFile, outputFile)
    inputFile.close()
    outputFile.close()
ownTimeRE = time.time()-ownTimeRE


#Imported parser

inputFile = open(inputFilePath, 'r', encoding='utf8')
outputFile = open(outputFilePath3, 'w', encoding='utf8')

importedTime = time.time()
for i in range(10):
    inputFile = open(inputFilePath, 'r', encoding='utf8')
    outputFile = open(outputFilePath3, 'w', encoding='utf8')
    importedXMLtoYAMLparse(inputFile, outputFile)
    inputFile.close()
    outputFile.close()
importedTime = time.time() - importedTime


print(f"Own time: {ownTime}\nOwn time with re: {ownTimeRE}\nImported time: {importedTime}")