Book into this system to book one or more books of a student's bad book. Offers go quick so, book it!

This is an application that is planned for creating a website that eases the pricess of exchanging used books within the ICS department.


Currently the database will be tracking entities based off of this diagram: ![Select](https://raw.github.com/kowasu/TheBooking/master/images/ERD%20for%20Book%20Exchange.png)


The ideology behind this is that students can make any number of requests or offers.

Requests and offers are specific to one student, book and condition. So if a student wants to request/offer multiple books, then they will create one for each book. When making a request or offer, the students will also be able to specify a price being sought.

Conditions are a set of predefined values which to describe the book's condition. Something along the lines of: Mint, Near-Mint, Light Wear, Moderate Wear, Heavy Wear, and Damaged.

The book table will contain all possible books to offer/request. A particular book can be requested/offered multiple times. The reason is that students will have their own copies. It will be identified as the same book because this makes it easier for searching and matching. Assurance that if someone is looking for "Elements of Java Style" then the person is offering who offers such a book, does infact have one. The price associated with the book is the MSRP price, as if it was bought from a store.

