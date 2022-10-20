from dataclasses import replace
import re
test1 = [
    "Rough rhythmic yellow agree;-{(able absurd beefy hot shaggy short dream ;-{(. Crooked white early van. Poor green ;-{(bulky attractive bashful teeny le;-{(mon house. Loose red dry vast motorcycle. Lemon bumpy mam;-{(moth wet plain autumn",
    "Abrasive absurd;-{( ram. Brown calm howling clumsy portugal. Fluffy curved brainy loose ability. Calm scruffy better rancid agitat;-{(ed thousands round thoughtless helmet. Chilly limited cool rapping straight anxious savory appalling deep daybreak",
    "Acrid;-{(antsy car. Round ;-{(small witty thankful shaggy square child. Grumpy gifted early best disgusting quaint wire. Wor;-{(ried abnormal brash purple red fluffy house. Petite harsh ;-{(florist",
    "Few ugly yummy scar;-{(y early alert businessperson. Chubby cool helpful dead alive witty cl;-{(ean lunch. Poor jolly powerful silly dusk. Scary rou;-{(nd panicky cool damp strong whale. Blushing br;-{(own freezing incalculable agreeab;-{(le window",
    "Hollow;-{( calm scruffy few cool daybreak. De;-{(afening some mel;-{(odic bright flabby quaint shaggy tasteless modern state. Deafenin;-{(g hollow salmon acoustic able nutriti;-{(;-{(ous scrawny wire. Scary;-{( gentle kitchen. Purring brash ashamed purple red adventu;-{(rous early juice"
] 

test2 = [
    "Clean bald bald pager. Immense Immense early insect. Uninterested damp ice",
    "Plump appalling egg. Clean dirty park. Hundreds shallow planet",
    "Acidic whispering army army. Abandoned acoustic photographer. Ripe Ripe shallow musician",
    "Modern poor toothbrush toothbrush. Rapid adventurous lifeguard. Abnormal lemon baby",
    "Anxious dead dead camera. Careful Careful tasty carpet. Rotten Rotten brash hair"
]

test3 = [
    "12 + 256 = 123",
    "31678 + 367927 = 666",
    "228 + 1337 = 7",
    "777 + 80085 = 8008135",
    "123 + 312 = 123321"
]

print("-" * 120)

#Task 1
for i in range(0, 5):
    print("*" * 120)
    print("Test", i, ": ")
    print("Input:\n    ", test1[i])
    print("Output:\n    There are ", len(re.findall(r";-{\(", test1[i])), " matches")


print("-" * 120)

#Task 2
for i in range(0, 5):
    print("*" * 120)
    print("Test", i, ": ")
    print("Input:\n    ", test2[i])
    regExResults = re.findall(r"\b(\w+)\s+\1\b", test2[i])
    for j in range(0, len(regExResults)):
        test2[i] = test2[i].replace(regExResults[j] + " ", "", 1)
    print("Output:\n    ", test2[i])

print("-" * 120)

for i in range(0, 5):
    print("*" * 120)
    print("Test", i, ": ")
    print("Input:\n    ", test2[i])
    regExResults = re.findall(r"\d+", test3[i])
    for j in range(0, len(regExResults)):
        test3[i] = test3[i].replace(regExResults[j], str(4 * int(regExResults[j]) ** 2 - 7))
    print("Output:\n    ", test3[i])
    
print("-" * 120)