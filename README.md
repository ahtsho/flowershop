# 🌹Welcome to Flower Shop!🌼

This application has been developed as a programming test for iCare.
   
 ## Test Instructions
A flower shop used to base the price of their flowers on an item by item cost. So if a customer ordered 10 roses then they would be charged 10x the cost of single rose. The flower shop has decided to start selling their flowers in bundles and charging the customer on a per bundle basis. So if the shop sold roses in bundles of 5 and 10 and a customer ordered 15 they would get a bundle of 10 and a bundle of 5.

The flower shop currently sells the following products:

|Name|Code|Bundle|
|-|-|-|
|Roses|`R12`|`5 @ $6.99, 10 @ $12.99`|
|Lillies|`L09`|`3 @ $9.95, 6 @ $16.95, 9 @ $24.95`|
|Tulips |`T58`|`3 @ $5.95, 5 @ $9.95, 9 @ $16.99`|

**Task**
Given a customer order you are required to determine the cost and bundle breakdown for each product. 
To save on shipping space each order should contain the minimal number of bundles.

**Input**
Each order has a series of lines with each line containing the number of items followed by the product code

*An example input*

    10 R12
    15 L09
    13 T58

**Output**
A successfully passing test(s) that demonstrates the following output: 
(The format of the output is not important)

    10 R12 $12.99
      1 x 10 $12.99
    15 L09 $41.90
      1 x 9 $24.95
      1 x 6 $16.95
    13 T58 $25.85
      2 x 5 $9.95
      1 x 3 $5.95

## Project Flower Shop
**Language** Java. Developed and tested on OpenJDK 17.07


## How to run it
# Demo mode

To tun the program in demo mode with the sample input in the example above pass it the argument `-t`

    java com.icare.flowershop.ConsoleFlowershopRunner -t
the output should be 

    -------------------------------
    10 R12 $12.99
       1 x 10 $12.99
    15 L09 $41.90
       1 x 9 $24.95
       1 x 6 $16.95
    13 T58 $25.85
       2 x 5 $9.95
       1 x 3 $5.95
    -------------------------------


# Interactive mode
If run with no arguments (really without `-t`) it should run in interactive mode on a your console.

    java com.icare.flowershop.ConsoleFlowershopRunner
It shows a splash screen of sorts with a welcome message and instructions on how to use it. 
You can insert as many lines of `number code` in order to place an order and when you are ready press Enter twice. 
It should either give you the total of each line and it's breakdown into items or give you either of two errors wrong input or non existing bundle eg:

    -------------------------------
    1 R12 BUNDLE IS NOT AVAILABLE!
    -------------------------------


Here's an example of complex positive reponses


    10 R12
    8 R12
    6 R12
    5 R12

    Thank you for your order!
    Here's the cost and bundle breakdown for each product.
    
    -------------------------------
    10 R12 $12.99
       1 x 10 $12.99
    8 R12 BUNDLE IS NOT AVAILABLE!
    6 R12 BUNDLE IS NOT AVAILABLE!
    5 R12 $6.99
       1 x 5 $6.99
    -------------------------------
    
    Goodbye! See you next time!🌸 

