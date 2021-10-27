
DROP TABLE tickets IF EXISTS;

CREATE TABLE tickets (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	creationDate VARCHAR(255),
	status VARCHAR(255),
	userCreator VARCHAR(255),
	assigneeUser VARCHAR(255),
	typeOfTicket VARCHAR(255),
	priority VARCHAR(255),
	position VARCHAR(255),
	hardwareToBeChanged VARCHAR(255),
	commentsID TEXT
);

CREATE TABLE comments (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	ticketID INTEGER,
	creationDate VARCHAR(255),
	creator VARCHAR(30),
	commentType VARCHAR(30),
	comment TEXT
);

DROP TABLE EMPLOYEE IF EXISTS;

CREATE TABLE employee (
	employeeID INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255),
	email VARCHAR(255),
	position VARCHAR(255),
	numAssignTicks int
);

DROP TABLE USERS IF EXISTS;

CREATE TABLE users (
	userID INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255),
	email VARCHAR(255),
	numTickets int
);

