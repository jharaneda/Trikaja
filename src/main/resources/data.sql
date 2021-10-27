
insert into tickets (creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2020-12-12 03:38:28', 'pending', 'jhattigan0', 'csuerz0', 'software', 'normal', 1, 'Colville','1');
insert into tickets (creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-03-22 05:48:51', 'solved', 'dbeveridge1', 'rricioppo1', 'software', 'normal', 35, 'Black or African American', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-08-06 08:34:13', 'solved', 'lcadge2', 'rjoselin2', 'software', 'high', 24, 'Shoshone', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-03-09 15:13:13', 'pending', 'gtidd3', 'tlynn3', 'software', 'normal', 47, 'Chinese', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2020-11-14 02:34:25', 'open', 'jkilbourne4', 'mmughal4', 'hardware', 'normal', 18, 'Paiute', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-10-21 14:18:45', 'solved', 'fjuan5', 'kgentsch5', 'software', 'normal', 18, 'Bangladeshi', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-07-27 02:03:06', 'open', 'shatchette6', 'pgabala6', 'software', 'high', 31, 'Creek', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-01-09 23:34:28', 'solved', 'kbream7', 'lorange7', 'hardware', 'high', 32, 'Uruguayan', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2020-10-24 06:30:25', 'open', 'rjeans8', 'tboase8', 'software', 'normal', 40, 'Puget Sound Salish', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-03-27 06:46:56', 'open', 'edallimare9', 'cmonini9', 'software', 'low', 35, 'Ottawa', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2020-12-20 01:38:13', 'pending', 'aconninghama', 'ssothama', 'hardware', 'low', 31, 'Spaniard', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-07-29 12:30:20', 'pending', 'dboowb', 'talvesb', 'hardware', 'normal', 37, 'Asian', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-10-06 03:31:09', 'open', 'dhanselmanc', 'ashevillc', 'hardware', 'low', 18, 'Alaskan Athabascan', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-08-02 06:54:48', 'open', 'nwhaymand', 'heverald', 'software', 'normal', 30, 'Uruguayan', '1');
insert into tickets(creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values ('2021-06-18 16:46:16', 'solved', 'ngoodbodye', 'jrayworthe', 'hardware', 'low', 23, 'Pima', '1');

insert into comments (ticketID, creationDate, creator, commentType, comment) values (1, '2021-10-22 22:52:03', 'jharanedac', 'public', 'some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 some large text 1 ');
insert into comments (ticketID, creationDate, creator, commentType, comment) values (1, '2021-10-22 23:52:03', 'jharanedac', 'public', 'some large text 2 ');

insert into employee (name, email, position, numAssignTicks) values ('Kadyn Neale', 'Kneale95@hotmail.ca', 'Manager', 0);
insert into employee (name, email, position, numAssignTicks) values ('Jon Doe', 'JDoe@gmail.com', 'Manager',0);
insert into employee (name, email, position, numAssignTicks) values ('Mary Sue', 'MSue@shaw.ca', 'Manager',0);
insert into employee (name, email, position, numAssignTicks) values ('Hubert Farnsworth','Farnsworth@planetexpress.future', 'Agent',0);
insert into employee (name, email, position, numAssignTicks) values ('Luigi','Luigi@mushroomkingdom.com','Agent',0);
insert into employee (name, email, position, numAssignTicks) values ('Donkey Kong', 'Bannanas@Kong.com', 'Agent',0);


insert into users (name, email, numTickets) values ('Phoenix Wright' , 'Attention@lawyer.com', 0);
insert into users (name, email, numTickets) values ('Monkey D Luffy', 'MDLuffy@strawhat.com', 0);
insert into users (name, email, numTickets) values ('Ash Ketchem', 'AshKet@pallet.town', 0);
