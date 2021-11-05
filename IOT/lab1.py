str = input()
dic = {str[0]:str[2],str[4]:str[6],str[8]:str[10],str[12]:str[14]}
#print(dic.items())

for line in open("D:\symbols.txt"):
    name = line.strip()
    for j in range(0,len(name)):
        print(dic[name[j]],end="")
        if(j==len(name)-1): print("")

    #*:1,#:2,@:3,$:4

