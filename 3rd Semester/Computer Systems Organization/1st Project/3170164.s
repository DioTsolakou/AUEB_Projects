# Author : DHIOGJENI COLLAKU
# Date : 07/11/18
# Description : This program reads two user inserted integers and asks the user which is their greatest common divisor. 
#		If the user answers correctly the program congratulates the user, if not, the user is asked to try again.

# $a0 to store the addresses of the strings included in .data
# $a1 to store the first user inserted integer
# $a2 to store the second user inserted integer
# $a3 to store the remainder of the division of the first by the second integer
# $s0 to store the user's answer on the question "Which is the gcd of the first and second integer

	.text
	.globl main

main:
	# print (“ Give an integer ”);
	la $a0, str1	# loads str1 address to $a0
	li $v0, 4	# loads $v0 with the contents of $a0 to print it
	syscall		# prints str1

	# a = readInteger();
	li $v0, 5	# reads the first user inserted integer from the console and stores it to $v0
	syscall		# executes the previous commands
	add $a1, $v0, $zero	# stores the first integer from $v0 to $a1
	

	# print (“ Give an integer ”);
	la $a0, str2	# loads str2 address to $a0
	li $v0, 4	# loads $v1 with the contents of $a0 to print it
	syscall		# prints str2

	# b = readInteger();
	li $v0, 5	# reads the second user inserted integer from the console and stores it to $v0
	syscall		# executes the previous commands
	add $a2, $v0, $zero	# stores the second integer from $v0 to $a2
	
	# print (“ Which is the gcd of “+ a + “ and “ + b + “? ”);
	la $a0, str3	# loads str3 address to $a0
	li $v0, 4	# loads $v0 with the contents of $a0 to print it
	syscall		# prints str3

	jal gcd		# calls the method named gcd
	jal printQuestion	# calls the method named printQuestion
	

gcd:
	# y = a % b;
	rem $a3, $a1, $a2	# stores the remainder of the division of the first integer with the second to $a3

	# while (y != 0) {
	#	a = b;
	#	b = y;
	#	y = a % b;
	# }
	again:
		# y = 0; break;
		beq $a3, $zero, exit	# if the contents of $a3 equal zero, go to exit
		# a = b;
		add $a1, $a2, $zero	# stores the contents of the 2nd integer to the register of the 1st
		# b = y;
		add $a2, $a3, $zero	# stores the contents of y to the register of the 2nd integer
		# y = a % b;
		rem $a3, $a1, $a2	# stores the remainder of the division of the first integer with the second to $a3
		j again			# jumps to again to do the next iteration of the loop
	exit:
		jr $ra			# return


printQuestion:
	loop:
		# s = readInteger();
		li $v0, 5	# reads the user's answer from the console and stores it to $v0
		syscall		#executes the previous commands
		add $s0, $v0, $zero	# stores the user's answer from $v0 to $s0
		
		# s = b;
		beq $s0, $a2, end	# if the contents of $s0 are equal with the contents of $a2 go to end
		la $a0, str5	# loads str5 address to $a0
		li $v0, 4	# loads $v0 with the contents of $a0 to print it
		syscall		# prints str5
			
		la $a0, str6	# loads str6 address to $a0
		li $v0, 4	# loads $v0 with the contents of $a0 to print it
		syscall		# prints str6
		j loop		# jumps to loop
	end:
		la $a0, str4	# loads str4 address to $a0
		li $v0, 4	# loads $v0 with the contents of $a0 to print it
		syscall		# prints str4
		jr $ra


	.data
str1: .asciiz "Please insert the first integer : \n"	
str2: .asciiz "Please insert the second integer : \n"
str3: .asciiz "Which is the GCD of the first and second integer? \n"
str4: .asciiz "Congratulations! \n"
str5: .asciiz "Wrong answer. Please try again. \n"
str6: .asciiz "Which is the GCD? \n"