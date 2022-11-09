import re
test1 = [
    "Rough rhythmic yellow agree;-{(able absurd beefy hot shaggy short dream ;-{(. Crooked white early van. Poor green ;-{(bulky attractive bashful teeny le;-{(mon house. Loose red dry vast motorcycle. Lemon bumpy mam;-{(moth wet plain autumn",
    "Abrasive absurd;-{( ram. Brown calm howling clumsy portugal. Fluffy curved brainy loose ability. Calm scruffy better rancid agitat;-{(ed thousands round thoughtless helmet. Chilly limited cool rapping straight anxious savory appalling deep daybreak",
    "",
    "Few ugly yummy scar;-{(y early alert businessperson. Chubby cool helpful dead alive witty cl;-{(ean lunch. Poor jolly powerful silly dusk. Scary rou;-{(nd panicky cool damp strong whale. Blushing br;-{(own freezing incalculable agreeab;-{(le window",
    12
] 

test2 = [
    12.2,
    "",
    "Acidic itmoitmoitmo whispering army army. Abandoned acoustic photographer. Ri-p-e Ri-p-e shallow musician",
    "Modern poor toothbrush toothbrush. Rapid adventurous lifeguard. Abnormal lemon baby",
    "Modern poor toothbrush toothbrush. Rapid adventurous lifeguard. Abnormal lem3on-le3mon baby",
    "Modern poor toothbrush toothbrush. Rapid adventurous lifeguard. Abnormal lemooon          lemooon baby",
    "Anxious dead dead camera. Careful careful tasty carpet. Rotten Rotten brash hair"
]

test3 = [
    'a',
    "31678 + 367927 = 399605",
    "",
    "777 + 80.085 = 80862",
    "123 + 312 = 444"
]

print("\n\n", "-" * 120, "\n\n")

#Task 1
for i in range(0, len(test1)):
    print("*" * 120)
    print("Test", i + 1, ": ")
    print("Input:\n    ", test1[i])
    if (isinstance(test1[i], str)):
        print("Output:\n    There are ", len(re.findall(r";-{\(", test1[i])), " matches")
    else:
        print("Input is not string")
    


print("\n\n", "-" * 120, "\n\n")


#Task 2
for i in range(0, len(test2)):
    print("*" * 120)
    print("Test", i + 1, ": ")
    print("Input:\n    ", test2[i])
    if (isinstance(test2[i], str)):
        regExResults = re.findall(r"(?i)\b([\w+-*]+)(\s+)(\1)\b", test2[i])
        for j in range(0, len(regExResults)):
            if (len(regExResults[j][1])> 1):
                test2[i] = test2[i].replace(regExResults[j][0], "", 1)
                test2[i] = test2[i].replace(regExResults[j][1], "")
            else:
                test2[i] = test2[i].replace(regExResults[j][2] + " ", "", 1)
        print("Output:\n    ", test2[i])
    else:
        print("Input is not string")

print("\n\n", "-" * 120, "\n\n")

for i in range(0, len(test3)):
    print("*" * 120)
    print("Test", i + 1, ": ")
    print("Input:\n    ", test3[i])
    if (isinstance(test2[i], str)):
        regExResults = re.findall(r"\d+\.*\d*", test3[i])
        for j in range(0, len(regExResults)):
            if "." in regExResults[j]:
                test3[i] = test3[i].replace(regExResults[j], "{:.4f}".format(4 * float(regExResults[j]) ** 2 - 7))
            else:
                test3[i] = test3[i].replace(regExResults[j], str(4 * int(regExResults[j]) ** 2 - 7))
        print("Output:\n    ", test3[i])
    else:
        print("Input is not string")
    
print("\n\n", "-" * 120, "\n\n")