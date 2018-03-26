# Search-Trees
Work about search trees for the subject of Algorithm and Data Structures at University.

Take in count that my work may have some errors.

Problem statement:

The Star Alliance group has recently taken on the responsibility of managing an integration program for several airlines that integrate one of our airlines and spend points when traveling with other companies in the group.

You have been asked to work out an application that keeps a record of fast access to the cumulative miles of each frequent flyer card. The main objective is to optimize the mileage survey for a given card, as this information is frequently searched on the Star Alliance website and associated companies.

Each card is identified by a string of less than 15 characters (such as
example "TP298421777" or "LH2009364744"). 

Mileage value is always an integer.

The formats used for these fields are as follows:
    Card: string with no spaces and a maximum of 15 characters
    Miles: positive or negative integer value (credit / deduct miles)
    
Your program should read a sequence of lines (until you find a blank line), each of which corresponds to one of the following operations:
  UPDATE <Card> <Miles>
    If the card is not yet in the database it is added with Miles credit.
    If the card already exists the total amount of miles is updated with the credit or debit of Miles (depending on whether the value of miles is positive or negative).
  REMOVE <Card>
    Removes the card from the database, for example because its validity has expired.
    If the card is not in the database, this operation is simply ignored.
  BALANCE <Card>
    This looks for the card in the database.
    If the card is in the database, the available balance is shown:
      <Card> BALANCE <Balance>
    If the card is not in the database, the following information is displayed:
      <Card> UNEXISTENT
  PRINT
    To print the information of all the cards in the database,(alphabetically) and with the following format for
each passenger:
    <Card> BALANCE <Balance>
  END
    To finish running the application.
      
 EXAMPLE:
  INPUT:
    UPDATE TP199529030 35
    UPDATE TP111111111 40
    BALANCE TP111111111
    BALANCE LH2222222 
    UPDATE TP111111111 5
    UPDATE TP456321098 10
    BALANCE TP111111111
    UPDATE LH3333333 40
    UPDATE LH3333333 -23
    BALANCE LH3333333 
    UPDATE TP111111111 10
    UPDATE TP111111111 100 
    BALANCE TP111111111
    REMOVE TP111111111 
    UPDATE TP235888945 1 
    UPDATE TP456321098 10 
    PRINT
    END
  
  OUTPUT:
    TP111111111 BALANCE 40 
    LH222222222 UNEXISTENT 
    TP111111111 BALANCE 45
    LH3333333 BALANCE 17 
    TP111111111 BALANCE 155
    LH3333333 BALANCE 17 
    TP199529030 BALANCE 35
    TP235888945 BALANCE 1
    TP456321098 BALANCE 20 
