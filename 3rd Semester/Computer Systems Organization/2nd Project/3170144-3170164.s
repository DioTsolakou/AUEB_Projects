# Authors: EMMANOUIL SKANDALIS, DHIOGJENI COLLAKU
# Date: 09/01/2019
# Description: This program reads user inserted integers and stores them into a sparse array.


# Registers :
# 	$s0 array index
#	$s1 user menu choice
#	$s2 index for the display functions
#	$s3 index for sparse array B
#	$s4 index for sparse array C
#	$t0 user inserted integer in array
#	$t1 sparse array index 1 \ int i
#	$t2 sparse array index 2 \ int k


	.text
	.globl main
	
main:
	addi $s0, $zero, 0 # initialize the array index register to zero
	
	jal menu
	
#void readPin (int [] pin)
#{
#	// Διαβάζει έναν Πίνακα με πολλά μηδενικά στοιχεία
#		for (int i=0; i<pin.length; i++)
#			pin [i] = readInt ("Position " + i +" :");
#} //readPin	
readInputA:
	addi $s0, $zero, 0		# make the array index zero

	loop:
		beq $s0, 40, exit	# if the array index equals max length of the array(40 bytes) go to exit
		
		la $a0, inputIntA	#
		li $v0, 4			# print inputIntA
		syscall				#
		
		move $a0, $s0	#
		li $v0, 1		# print the current value of the array index
		syscall			#
		
		li $v0, 5			#
		syscall				# store the user's integer to the $t0 register
		move $t0, $v0		#
		
		sw $t0, ArrayA($s0)	# store the contents of $t0 to the specific index in the array
	
		addi $s0, $s0, 4	# increment the index by 4 bytes
		j loop
	exit:
		jr $ra
		
		
#void readPin (int [] pin)
#{
#	// Διαβάζει έναν Πίνακα με πολλά μηδενικά στοιχεία
#		for (int i=0; i<pin.length; i++)
#			pin [i] = readInt ("Position " + i +" :");
#} //readPin		
readInputB:
	addi $s0, $zero, 0		# make the array index zero

	loop_01:
		beq $s0, 40, exit_1	# if the array index equals max length of the array(40 bytes) go to exit
		
		la $a0, inputIntB	#
		li $v0, 4			# print inputIntB
		syscall				#
		
		move $a0, $s0	#
		li $v0, 1		# print the current value of the array index
		syscall			#
		
		li $v0, 5			#
		syscall				# store the user's integer to the $t0 register
		move $t0, $v0		#
		
		sw $t0, ArrayB($s0)	# store the contents of $t0 to the specific index in the array
	
		addi $s0, $s0, 4	# increment the index by 4 bytes
		j loop_01
	exit_1:
		jr $ra
		

#int createSparse (int [ ] pin, int [ ] Sparse)
#{
#	// Δημιουργεί έναν αραιό πίνακα (Sparse) και επιστρέφει το μήκος του
#	int i, k=0;
#	for (i=0; i<pin.length; i++)
#		if (pin [i] != 0){
#			Sparse [k++] = i;
#			Sparse [k++] = pin [i];
#		}
#	return k;
#}		
createSpA:
	addi $t1, $zero, 0	# make the index zero \ int i = 0
	addi $t2, $zero, 0	#make the index zero \ int k = 0
	
	for:
		beq $t1, 40, exit_2			# if the array index equals the max length of the array(40 bytes) go to exit_2
		beqz ArrayA($t1), increment # if the value at the indicated index is zero go to increment
		sw SparseA($t2), $t1		# store the contents of $t1 in the indicated by $t2 index in sparse array A
		addi $t2, $t2, 4			# increment $t2 by one integer \ 4 bytes
		sw SparseA($t2), ArrayA($t1)# store the value on the $t1 index from array A to sparse array A at the shown position by $t2
		j for
	increment:
		addi $t1, $t1, 4	# increment $t1 by 4 bytes
		j for
		
	exit_2:
		move $a0, $t2
		li $v0, 1		
		syscall		# print the integer that was in $t2
		jr $ra
		

#int createSparse (int [ ] pin, int [ ] Sparse)
#{
#	// Δημιουργεί έναν αραιό πίνακα (Sparse) και επιστρέφει το μήκος του
#	int i, k=0;
#	for (i=0; i<pin.length; i++)
#		if (pin [i] != 0){
#			Sparse [k++] = i;
#			Sparse [k++] = pin [i];
#		}
#	return k;
#}		
createSpB:
	addi $t1, $zero, 0	# make the index zero \ int i = 0
	addi $t2, $zero, 0	#make the index zero \ int k = 0
	
	for:
		beq $t1, 40, exit_3
		beqz ArrayB($t1), increment
		sw SparseB($t2), $t1
		addi $t2, $t2, 4
		sw SparseB($t2), ArrayB($t1)
		j for
	increment:
		addi $t1, $t1, 4
		j for
		
	exit_3:
		move $a0, $t2
		li $v0, 1
		jr $ra
		

#void printSparse (int [ ] Sparse, int mikos)
#{
#	// Εμφανίζει έναν αραιό πίνακα
#	for (int i=0; i<mikos;)
#		println ("Position: " + Sparse [i++] + " Value: " + Sparse [i++]);
#} //printSparse		
displaySpA:
	addi $s2, $zero, 0
	again:
		beq $s2, 80, exit_4
		
		la $a0, displayPosition		#
		li $v0, 4					# print displayPosition
		syscall						#
		
		move $a0, $s2	#
		li $v0, 1		# print the current value of the array index
		syscall			#
		
		addi $s2, $s2, 4	# increment the register by one integer

		la $a0, displayValue	#
		li $v0, 4				# print displayValue
		syscall					#
		
		lw $a0, SparseA($s2)	# load the value at the position $s2 indicates
		li $v0, 1				# get ready to print an integer
		syscall					# print the integer
		addi $s2, $s2, 4		# increment the register by one integer
		j again
		
	exit_4:
		jr $ra


#void printSparse (int [ ] Sparse, int mikos)
#{
#	// Εμφανίζει έναν αραιό πίνακα
#	for (int i=0; i<mikos;)
#		println ("Position: " + Sparse [i++] + " Value: " + Sparse [i++]);
#} //printSparse		
displaySpB:
	addi $s2, $zero, 0
	again:
		beq $s2, 80, exit_5
		
		la $a0, displayPosition #
		li $v0, 4				# print displayPosition
		syscall					#
		
		move $a0, $s2	#
		li $v0, 1		# print the current value of the array index	
		syscall			#
		
		addi $s2, $s2, 4	# increment the register by one integer

		la $a0, displayValue	#
		li $v0, 4				# print displayValue
		syscall					#
		
		lw $a0, SparseB($s2)	# load the value at the position $s2 indicates
		li $v0, 1				# get ready to print an integer
		syscall					# print the integer
		addi $s2, $s2, 4		# increment the register by one integer
		j again
		
	exit_5:
		jr $ra


#void printSparse (int [ ] Sparse, int mikos)
#{
#	// Εμφανίζει έναν αραιό πίνακα
#	for (int i=0; i<mikos;)
#		println ("Position: " + Sparse [i++] + " Value: " + Sparse [i++]);
#} //printSparse		
displaySpC:
	addi $s2, $zero, 0
	again:
		beq $s2, 80, exit_6
		
		la $a0, displayPosition #
		li $v0, 4				# print displayPosition
		syscall					#
		
		move $a0, $s2	#
		li $v0, 1		# print the current value of the array index
		syscall			#
		
		addi $s2, $s2, 4	# increment the register by one integer

		la $a0, displayValue	#
		li $v0, 4				# print displayValue
		syscall					#
		
		lw $a0, SparseC($s2)	# load the value at the position $s2 indicates
		li $v0, 1				# get ready to print an integer
		syscall					# print the integer
		addi $s2, $s2, 4		# increment the register by one integer
		j again
		
	exit_6:
		jr $ra


#int add (int [ ] SparseA, int mikosA, int [ ] SparseB, int mikosB, int [ ] SparseC)
#{
#	// Προσθέτει δύο αραιούς πίνακες και δημιουργεί έναν τρίτο αραιό πίνακα.
#	// Επιστρέφει το μήκος του νέου πίνακα.
#	int a,b,c;
#	for (a=0, b=0, c=0; a<mikosA && b<mikosB; )
#		if (SparseA[a] < SparseB [b]) {
#			SparseC [c++] = SparseA[a++];
#			SparseC [c++] = SparseA[a++];
#		}
#		else if (SparseA[a] > SparseB [b]) {
#			SparseC [c++] = SparseB[b++];
#			SparseC [c++] = SparseB[b++];
#		}
#		else { SparseC [c++] = SparseA[a++];
#			b++;
#			SparseC [c++] = SparseA[a++] + SparseB[b++];
#		} //if
#	for (;a<mikosA;) {
#		SparseC [c++] = SparseA[a++];
#		SparseC [c++] = SparseA[a++];
#	} // for
#	for (;b<mikosB;) {
#		SparseC [c++] = SparseB[b++];
#		SparseC [c++] = SparseB[b++];
#	} // for
#	return c;
#} //add

createSpC:
		addi $s0, $zero, 0	# make the index for sparse array A zero
		addi $s3, $zero, 0  # make the index for sparse array B zero
		addi $s4, $zero, 0	# make the index for sparse array C zero
		
	again:
		bge $s0, 80, exit_7	# a < mikosA
		bge $s3, 80, exit_7	# b < mikosB
		bgt SparseB($s3), SparseA($s0), if			# if (SparseA[a] < SparseB [b])
		bgt SparseA($s0), SparseB($s3), else_if		# else if (SparseA[a] > SparseB [b])
		beq SparseA($s0), SparseB($s3), else		# else \ if sparseA[a] == sparseB[b]
		
	if:	
		lw SparseC($s4), SparseA($s0)	#
		addi $s4, $s4, 4				# 
		addi $s0, $s0, 4				#
		syscall							# SparseC [c++] = SparseA[a++];
										# SparseC [c++] = SparseA[a++];
		lw SparseC($s4), SparseA($s0)	#
		addi $s4, $s4, 4				#
		addi $s0, $s0, 4				#
		syscall
		j again
		
	else_if:
		lw SparseC($s4), SparseB($s3)	#
		addi $s4, $s4, 4				#
		addi $s3, $s3, 4				#
		syscall							# SparseC [c++] = SparseB[b++];
										# SparseC [c++] = SparseB[b++];
		lw SparseC($s4), SparseB($s3)	#
		addi $s4, $s4, 4				#
		addi $s3, $s3, 4				#
		syscall
		j again
		
	else:
		lw SparseC($s4), SparseA($s0)				#
		addi $s3, $s3, 4							#
		syscall										#
													# b++;
		add SparseC($s4), SparseA($s0), Sparse($s3) # SparseC [c++] = SparseA[a++] + SparseB[b++];
		addi $s0, $s0, 4							#
		addi $s3, $s3, 4							#
		addi $s4, $s4, 4							#
		syscall
		j again
		
	exit_7:
		j for_01
		
	for_01:
		bge $s0, 80, exit_01	# a < mikosA
		
		lw SparseC($s4), SparseA($s0)	#
		addi $s4, $s4, 4				#
		addi $s0, $s0, 4				#
		syscall							# SparseC [c++] = SparseA[a++];
										# SparseC [c++] = SparseA[a++];
		lw SparseC($s4), SparseA($s0)	#
		addi $s4, $s4, 4				#
		addi $s0, $s0, 4				#
		syscall
		j for_01
		
	exit_01:
		j for_02
		
	for_02:
		bge $s3, 80, exit_02	# b < mikosB
		
		lw SparseC($s4), SparseB($s3)	#
		addi $s4, $s4, 4				#
		addi $s3, $s3, 4				#
		syscall							#
										# SparseC [c++] = SparseB[b++];
		lw SparseC($s4), SparseB($s3)	# SparseC [c++] = SparseB[b++];
		addi $s4, $s4, 4				#
		addi $s3, $s3, 4				#
		syscall							#
		j for_02
		
	exit_02:
		la $a0, $s4	# load the contents of $s4 to $a0 \ return c
		li $v0, 1	# get ready to print an integer \ return c
		syscall		# return c
		jr $ra
		
menu:
	la $a0, menu	#
	li $v0, 4		# print the menu
	syscall			#
	
	la $a0, exitmenu	#
	li $v0, 4			# print the choice to exit
	syscall				#
	
	la $a0, waitInput	#
	li $v0, 4			# wait for the user's choice
	syscall				#
	
	li $v0, 5		#
	syscall			# store the user's choice in register $s1
	move $s1, $v0	#
	
	while:
		beqz $s1, quit						# if the user's choice equals to zero go to quit
		beq $s1, firstChoice, choice1		# if the user's choice equals to 1 go to choice1
		beq $s1, secondChoice, choice2		# if the user's choice equals to 2 go to choice2
		beq $s1, thirdChocie, choice3		# if the user's choice equals to 3 go to choice3
		beq $s1, fourthChoice, choice4		# if the user's choice equals to 4 go to choice4
		beq $s1, fifthChoice, choice5		# if the user's choice equals to 5 go to choice5
		beq $s1, sixthChocie, choice6		# if the user's choice equals to 6 go to choice6
		beq $s1, seventhChoice, choice7		# if the user's choice equals to 7 go to choice7
		beq $s1, eighthChoice, choice8		# if the user's choice equals to 8 go to choice8
		j while
		
	choice1:
		jal readInputA
		j menu
		
	choice2:
		jal readInputB
		j menu
		
	choice3:
		jal createSpA
		j menu
		
	choice4:
		jal createSpB
		j menu
		
	choice5:
		jal createSpC
		j menu
		
	choice6:
		jal displaySpA
		j menu
		
	choice7:
		jal displaySpB
		j menu
		
	choice8:
		jal displaySpC
		j menu
	quit :
		li $v0, 10	#exit the program
		syscall
		
		
		
		
	
	.data
ArrayA : .space 40
ArrayB : .space 40
SparseA : .space 80
SparseB : .space 80
SparseC : .space 80
inputIntA : .asciiz "Please give an integer to store in array A at the position : "
inputIntB : .asciiz "Please give an integer to store in array B at the position : "
menu : .ascii "1. Read Array A\n
				 2. Read Array B\n
				 3. Create Sparse Array A\n
				 4. Create Sparse Array B\n
				 5. Create Sparse Array C = A + B\n
				 6. Display Sparse Array A\n
				 7. Display Sparse Array B\n
				 8. Display Sparse Array C\n
				 -----------------------------\n"
exitmenu : .asciiz "0. Quit"
waitInput : .asciiz "Choice? "
firstChoice : .word 1
secondChoice : .word 2
thirdChoice : .word 3
fourthChoice : .word 4
fifthChoice : .word 5
sixthChoice : .word 6
seventhChoice : .word 7
eighthChoice : .word 8
displayPosition : .asciiz "Position : "
displayValue : .asciiz "Value : "