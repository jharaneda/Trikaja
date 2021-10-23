CREATE TABLE ticketHardware (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	creationDate VARCHAR(255),
	status VARCHAR(255),
	userCreator VARCHAR(255),
	assigneeUser VARCHAR(255),
	typeOfTicket VARCHAR(255),
	priority VARCHAR(255),
	position VARCHAR(255),
	hardwareToBeChanged VARCHAR(255),
	comments TEXT
);