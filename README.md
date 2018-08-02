# Fox Automation Test


Project Details/ Instructions:

Please create a working selenium project that basically opens www.fox.com and then create an account.  Then after that:

1)      Log in to https://www.fox.com

2)      Navigate to Shows Tab FOX, scroll down and capture last 4 shows and save it on Excel sheet under column Title “Show”

a.       24 Hours To Hell & Back

b.      So You Think You Can Dance

c.       Meghan Markle: An American Princess

d.      Hypnotize Me

3)      Now Click on “FX” Link and verify all the above 4 shows are displayed.

4)      Similarly click on “National Geographic” , “Fox sports” and “All Shows” and verify all the above 4 shows displayed / duplicated.

5)      If it’s displayed / duplicated in any of the 4 tabs capture them in excel sheet as “Duplicate Record”


------------------------------------------------------------------------------------------------------------------


There is 1 test class and 4 page object classes.

I separated the requirements into 2 different test cases on the test class.

First Test Case covers: Creating an account on fox.com

Second Test Case covers: Signing into fox with newly created user account, navigating to shows tab, capturing last 4 shows and writing it out to an excel file,
verifying the 4 shows listed in the requirements on the specified tabs, writing out duplicated shows to same excel file on new sheet

Test cases can be ran individually or or as a suite of tests by clicking "run" by the class declaration

I created 4 page object classes. One for fox homepage, account page, create a profile page, and sign in page
