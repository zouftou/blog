INSERT INTO users (id,email,password,display_name,roles,locked_account,trusted_account)
VALUES (1,'admin@blog.com','Passwd12345','Admin', ARRAY [0,2], false,true);

INSERT INTO users (id,email,password,display_name, roles,locked_account,trusted_account)
VALUES (2,'author@blog.com','Passwd12345','Author', ARRAY [1,2], false,true);

INSERT INTO users (id,email,password,display_name,roles,locked_account,trusted_account)
VALUES (3,'user@blog.com','Passwd12345','User', ARRAY [2],false,true);

INSERT INTO blogs (id,title,content,published,tags,published_time,created_time,author_id)
VALUES (1,'blog title 1','blog content 1',true, 'Java,Spring','2023-10-16 14:30:00','2023-10-13 14:30:00',2);

INSERT INTO blogs (id,title,content,published,tags,published_time,created_time,author_id)
VALUES (2,'blog title 2','blog content 2',false, 'Angular, Css','2023-10-16 13:30:00','2023-10-12 14:30:00', 2);

INSERT INTO blogs (id,title,content,published, published_time,tags, author_id)
VALUES (3,'blog title 3','blog content 3',true, '2023-01-05', 'Vue-js,Node', 1);

INSERT INTO comments (id,content,status,blog_id,user_id)
VALUES (1,'comment content 1',1,1,3);

INSERT INTO comments (id,content,status,created_time,blog_id,user_id)
VALUES (2,'comment content 2',1,'2023-10-12 10:30:00',2,2);

INSERT INTO comments (id,content,status,created_time,blog_id,user_id)
VALUES (3,'comment content 3',0,'2023-10-12 12:30:00',3,1);

INSERT INTO comments (id,content,status,created_time,blog_id,user_id)
VALUES (4,'comment content 4',0,'2023-10-12 11:30:00',3,2);

INSERT INTO comments (id,content,status,created_time,blog_id,user_id)
VALUES (5,'comment content 5',0,'2023-10-12 12:30:00',2,2);