instructions = "

#define the questions and their answers
q1 = "Which insect kills the male during sexual intercourse?"
a1a = "Bee"
a1b = "Mosquito"
a1c = "Praying Mantis"
a1d = "Butterfly"
ra1 = 3
q2 = "Which of these spiders has the longest legs?"
a2a = "Giant Huntsman"
a2b = "Goliath"
a2c = "Tarantula"
a2d = "Black Widow"
ra2 = 1
q3 = "Which of the following is real?"
a3a = "spiderMan"
a3b = "Unicorn"
a3c = "Giant Squid"
a3d = "Kraken"
ra3 = 3
q4 = "Which of these swims the fastest?"
a4a = "Michael Phelps"
a4b = "Great White Shark"
a4c = "Black Marlin Swordfish"
a4d = "Indo-Pacific Sailfish"
ra4 = 3
q5 = "Which of these animals has the best smelling ability?"
a5a = "Human"
a5b = "Dog"
a5c = "Pig"
a5d = "Goldfish"
ra5 = 2
q6 = "Which of these animals can be found only in Greece?"
a6a = "Kri-Kri"
a6b = "Brown Bear"
a6c = "American Eagle"
a6d = "Tuna"
ra6 = 1
q7 = "Which of these animals lives the longest?"
a7a = "Tiger"
a7b = "Turtle"
a7c = "Rabbit"
a7d = "Cow"
ra7  = 2
q8 = "Which of the following is hunted the most for its fur?"
a8a = "Beavers"
a8b = "Pig"
a8c = "Cat"
a8d = "Rat"
ra8 = 1
q9 = "Which of the following animals has gone extinct?"
a9a = "Bengal Tiger"
a9b = "White Elephant"
a9c = "Siamese Cat"
a9d = "Dodo"
ra9 = 4
q10 = "Which of the following runs the fastest?"
a10a = "Cheetah"
a10b = "Usain Bolt"
a10c = "Sloth"
a10d = "Horse"
ra10 = 1
q11 = "Which of these animals has been domesticated for the longest time?"
a11a = "Sheep"
a11b = "Cat"
a11c = "Dog"
a11d = "Dunkey"
ra11 = 3

questions = [ [q1, a1a, a1b, a1c, a1d, ra1],[q2, a2a, a2b, a2c, a2d, ra2],
              [q3, a3a, a3b, a3c, a3d, ra3], [q4, a4a, a4b, a4c, a4d, ra4],
              [q5, a5a, a5b, a5c, a5d, ra5], [q6, a6a, a6b, a6c, a6d, ra6],
              [q7, a7a, a7b, a7c, a7d, ra7], [q8, a8a, a8b, a8c, a8d, ra8],
              [q9, a9a, a9b, a9c, a9d, ra9], [q10, a10a, a10b, a10c, a10d, ra10],
              [q11, a11a, a11b, a11c, a11d, ra11] ]

              # q : question, aNi :η i επιλογή της Ν ερώτησης, ra : right answer

def main() :
    print(instructions)
    print()
    player1 = Player(input("Give the name of the first player: "))
    player2 = Player(input("Give the name of the second player: "))
    player3 = Player(input("Give the name of the third player: "))
    print() 

    for i in range(10) :
        player1.question(i,False)
        player2.question(i,False)
        player3.question(i,False)

    defineWinner(player1, player2, player3)

    print()
    end = input("press enter to exit the program ") 

def defineWinner(player1, player2, player3) :
    #Περιέργος ο πολλ/σμος των τύπων: (boolean)*(int) ειναι δυνατός!
    player1.score = player1.score + 5*player1.help1 + 5*player1.help2
    player2.score = player2.score + 5*player2.help1 + 5*player2.help2
    player3.score = player3.score + 5*player3.help1 + 5*player3.help2

    ranking = [player1,player2,player3]

    #ταξινόμηση
    if (ranking[2].score > ranking[1].score) :
        temp = ranking[1]
        ranking[1] = ranking[2]
        ranking[2] = temp
        
    if (ranking[1].score > ranking[0].score) :
        temp = ranking[0]
        ranking[0] = ranking[1]
        ranking[1] = temp

    if (ranking[2].score > ranking[1].score) :
        temp = ranking[1]
        ranking[1] = ranking[2]
        ranking[2] = temp
    #Καθορισμός κατάταξης και νικιτή/ων
    if (ranking[0].score == ranking[1].score == ranking[2].score) :
        # Περιέργος αυτός ο τρόπος διατυπωσης της συνθήκης λειτουργεί
        print("EVERYONE IS A WINNER!!!")
        print("1st) "+ranking[0].name+", score "+str(ranking[0].score))
        print("1st) "+ranking[1].name+", score "+str(ranking[1].score))
        print("1st) "+ranking[2].name+", score "+str(ranking[2].score))
        
    elif (ranking[0].score == ranking[1].score) :
        print("AND THE WINNERS ARE "+ranking[0].name+" AND "+ranking[1].name+" !!!")
        print("1st) "+ranking[0].name+", score "+str(ranking[0].score))
        print("1st) "+ranking[1].name+", score "+str(ranking[1].score))
        print("3rd) "+ranking[2].name+", score "+str(ranking[2].score))
        
    elif (ranking[1].score == ranking[2].score) :
        print("AND THE WINNER IS "+ranking[0].name+" !!!")
        print("1st) "+ranking[0].name+", score "+str(ranking[0].score))
        print("2nd) "+ranking[1].name+", score "+str(ranking[1].score))
        print("2nd) "+ranking[2].name+", score "+str(ranking[2].score))

    else :
        print("AND THE WINNER IS "+ranking[0].name+" !!!")
        print("1st) "+ranking[0].name+", score "+str(ranking[0].score))
        print("2nd) "+ranking[1].name+", score "+str(ranking[1].score))
        print("3rd) "+ranking[2].name+", score "+str(ranking[2].score))

    

class Player :
    def __init__(self, name) :
        self.name = name
        self.score = 0
        self.help1 = True #50-50, true : has not been used
        self.help2 = True #skip, true : has not been used

    # help1_in_use = True , αν κατά τη διάρκεια της κλήσης, η help1 χρησιμοποιείται
    
    def question(self, i, help1_in_use) :
        print() #E
        print("It's player's "+self.name+" turn!") 
        print(questions[i][0])
            
        if (help1_in_use) : #Η λίστα των δυνατών απαντήσεων αν χρησημοποιείται η 50-50 βοήθεια
            answer_list = ['1', str(max(2,questions[i][5]))]
            print("1) "+questions[i][1])
            print(str(max(2, questions[i][5]))+") "+questions[i][max(2,questions[i][5])])
            print()
        else : #Η λίστα των δυνατών απαντήσεων αν δεν χρησημοποιείται η 50-50 βοήθεια
            answer_list = ['1', '2', '3', '4']
            for j in range(1, 5) : 
                print(str(j)+") "+questions[i][j])                                               
            print()
            
        if (self.help1) :
            print("To use '50-50', insert h1")
        if (self.help2) :
            print("To use 'Skip this question', insert h2")
        print()

        
        while(True) : 
            answer = input("Give your answer ") 
            print() 
            if (answer in answer_list) : #ελέγχει αν η απάντηση ειναι στη λίστα των απαντήσεων
                if ( int(answer) == questions[i][5]) :
                    print("Right answer!!")
                    print()
                    self.score += 10
                else :
                    print("Wrong answer!!")
                    print()
                    print()
                break 
            elif (answer == "h1" and self.help1) :
                self.help1 = False 
                self.question(i,True)  
                break 
            elif (answer == "h2" and self.help2) :
                self.help2 = False
                self.question(10,False) 
                break 
            else:
                print("Wrong input, try again")
            
main()

