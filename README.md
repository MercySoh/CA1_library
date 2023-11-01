Web Patterns – CA1 – 30% - The DAO Pattern & Persistence
Page 1 of 2
This CA covers database interaction using the DAO pattern.
It is a group CA, with each group made up of 3 team members.
Note: This is part TWO of this CA, which slots into this same system structure you were building in part one.
New components are written in red.
You have been tasked with writing a basic library system. Your system should provide the following functionality:
1) Registering with the library (a member must provide more than just a username and password to register).
2) Logging in to your library account (by providing your username and password).
3) Viewing the details of all books in the library.
4) Viewing the details of all loans currently active for you.
5) Viewing the details of all loans you have made since joining the library.
6) Borrowing a copy of a book in the library (if there are no copies available in the library, this will not succeed). A
member can only borrow a single copy of any title in a single transaction (they can borrow multiple books at a
time, just no more than one copy of a specific title). However, once they return their copy of a book, they can
borrow it again in the future.
7) Returning a book you currently have on loan (late fees should be considered)
8) Viewing the details of any late fees you may currently have outstanding.
9) Paying a late fee (you do not need to take a payment, just check the card number is legitimate and expiry date is
valid).
10) Logging out.
In addition to general member functionality excluding registration (i.e. admins should also be able to perform functions
2-10 listed above), the system should also provide the following functionality only to admin users:
1) Adding a book to the library.
2) Increasing/decreasing the number of copies of a book in the library stock by x amount.
3) Disabling a member from the library (Note: An admin cannot disable another admin from the library).
You are required to:
1. Create a MySQL database to hold all of the required information for the library system to function in a welldesigned fashion. Primary and foreign keys should be included, and the database should include reasonable levels
of information, not merely the minimum required to allow the system to function.
2. Write the front-end interface for the system – this MUST be based on the command-line.
3. Write the set of Java classes that control access to the database & provide the program’s functionality. These
classes MUST implement the DAO pattern for all database access & you MUST create and use Data Transfer
Objects (DTOs).
4. Write a set of Junit tests for all table-specific DAO classes (no need for DTOs to be tested)
5. Write a set of Junit tests for a DAO, using Mockito to mock all database interaction. You may choose any DAO to
include mock tests for, provided it contains a minimum of three methods (all of which must be fully tested).
6. Write Javadoc comments for all DAO methods as well as any other non-DTO code. These should include
information on return types, parameters, method functionality and the meaning of any potential exceptions that
can be thrown.
Marks will be awarded as follows:
• Implementation of the DAO pattern, construction of DAO classes & provision of database functionality - 40%
• Database design and population of tables with dummy information – 10%
• Program interface – 15%
• Junit tests (including mocking) – 25%
• Javadocs – 10%
Web Patterns – CA1 – 30% - The DAO Pattern & Persistence
Page 2 of 2
Project management:
You are required to create and maintain source control via git and github. Code should be regularly committed (with
appropriate commit messages indicating what is being added/changed/removed/fixed etc) and pushed to the shared
repository by all team members.
Group work balance:
Each class’s owner(s) should be listed using the @author Javadoc annotation at the top of the class. Where more than
one student has worked on a class, they should be listed as an author. However, each student must take full ownership
of at least one DAO class (and its corresponding unit tests, interface and DTO). Each student must complete at least one
set of mock tests (i.e. they should write all unit tests using mock objects for at least one method).
Submission instructions:
Upload a zipped copy of your Intellij project to Moodle, as well as a link to your project on GitHub. This zipped file MUST
also include files containing the SQL statements used to create and populate your MySQL “live” and test databases. If
these files are not included, your project will not be marked.
Deadline: 23:59 on 5
th November 2023.
