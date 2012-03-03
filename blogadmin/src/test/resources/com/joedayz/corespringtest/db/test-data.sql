insert  into tema(ID,DESCRIPCION) values (1,'Container'); 
insert  into tema(ID,DESCRIPCION) values (2,'AOP'); 
insert  into tema(ID,DESCRIPCION) values (3,'Data'); 
insert  into tema(ID,DESCRIPCION) values (4,'Security'); 
insert  into tema(ID,DESCRIPCION) values (5,'Web'); 
insert  into tema(ID,DESCRIPCION) values (6,'Web Services'); 
insert  into tema(ID,DESCRIPCION) values (7,'Integration'); 
insert  into tema(ID,DESCRIPCION) values (8,'Batch'); 
insert  into tema(ID,DESCRIPCION) values (9,'SpringSource Tool Suite'); 
insert  into tema(ID,DESCRIPCION) values (10,'ROO');

insert  into post(ID,TITULO,DESCRIPCION,ID_TEMA,FECHA_CREACION) values (1,'Issue with basic dependency injection','Hi Guys,\r\n\r\nI have a scenario where I need to inject values to the Arraylist in a class that does not have the setter I believe in this scenario I need to do a get on the list in the POC class and then do a add:\r\n\r\npublic class POC {\r\n\r\nprivate ArrayList<String> beheaviour=new ArrayList<String>();\r\n\r\npublic ArrayList<String> getBeheaviour() {\r\nreturn beheaviour;\r\n}\r\n\r\n\r\n}\r\nHere is the xml mapping code :\r\n\r\n<bean id=\"poc\" class=\"outBoundocument.factory.POC\">\r\n<property name=\"beheaviour\">\r\n<list>\r\n<value>temp1</value>\r\n<value>temp2</value>\r\n<value>temp3</value>\r\n<value> temp4</value>\r\n<value>temp5</value>\r\n</list>\r\n</property>\r\n</bean>\r\n\r\nthe following code returns :\r\n\r\nError setting property values; nested exception is org.springframework.beans.NotWritablePropertyExcep tion: Invalid property \''beheaviour\'' of bean class [outBoundocument.factory.POC]: Bean property \''beheaviour\'' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?\r\n\r\n\r\nMoreover there is no way for me to modify the POC class so I will have to perform a property Injection some how. The Java translation of the above is as simple as\r\n\r\nPOC poc =new POC ();\r\npoc.getBeheaviour.add(\"crap\") ',1,CURRENT_DATE()); 
insert  into post(ID,TITULO,DESCRIPCION,ID_TEMA,FECHA_CREACION) values (2,'Spring transaction with JPA and stored procedure','HI,\r\n\r\nWe are using jpa with spring transaction. (Oracle database)\r\n\r\nWithin a transaction i am inserting record in one table(using jpa) then i am passing the id to stored procedure (spring storedprocedure handler). But inside the stored procedure, the data can\''t retrieved. because, no record exists with this id on that table.\r\n\r\nNote: Insertion is happening with jpa and stored procedure is being called using spring storedprocedure handler.\r\n\r\nPlease guide me to go ahead. Anything i need to do with transactions and its configurations.\r\n\r\nThanks in advance ',3,CURRENT_DATE());

insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (1,'rishishehrawat','rishishehrawat@mail.com','From what you have described it seems like the insertion using JPA & select in SP are being done in different transactions. This is the reason data inserted by JPA is not visible in SP. ',2);
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (2,'Susan Inga','ic.susan@gmail.com','Not use JPA.',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (3,'rishishehrawat','rishishehrawat@mail.com','The only way to make this work is\r\n\r\n1.) Either both operations are done in same transaction (uncommitted data is visible only in same transaction, unless you are running the transaction with READ_UNCOMMITTED isolation level)\r\n2.) The JPA transaction that has inserted should have already committed its transaction before the SP goes & reterieves on ID\r\n\r\nIf both JPA & SP are running in 2 seperate transactions then the JPA insert will not be visible to SP select. ',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (4,'Susan Inga','ic.susan@gmail.com','Not JPAAAAAAA',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (5,'Susan Inga','ic.susan@gmail.com','JPAAAAAAAAAAAA',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (6,'Susan Inga','ic.susan@gmail.com','Nooooooooooooooooooooo',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (7,'Susan Inga','ic.susan@gmail.com','wiiiiiiiiiiiii',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (8,'Susan Inga','ic.susan@gmail.com','weeeeeeeeeeeeeeeeee',2); 
insert  into comentario(ID,NOMBRE,CORREO_ELECTRONICO,COMENTARIO,ID_POST) values (9,'Susan Inga','ic.susan@gmail.com','sssssssssssssssssss',2);

