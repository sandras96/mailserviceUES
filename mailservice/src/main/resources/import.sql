insert into euser (user_firstname, user_lastname, user_password, user_username) values ("sandra", "stojanovic", "$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK", "sakica");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("nela", "milojevic", "$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK", "nekica");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("marko", "cvijovic", "$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK", "make");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("nikola", "spasojevic", "$2a$04$bAsNVI05EjajIzH4AHFdu.RimWMl2K5hmnzeBrcqRX7Cm8BtMgIFK", "sake");

INSERT INTO authority(name)VALUES('ADMIN');
INSERT INTO authority(name)VALUES('USER');

INSERT INTO user_authority(user_id,authority_id)VALUES(1,1);
INSERT INTO user_authority(user_id,authority_id)VALUES(2,1);
INSERT INTO user_authority(user_id,authority_id)VALUES(3,2);
INSERT INTO user_authority(user_id,authority_id)VALUES(4,2);

insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("megs", "megs@gmail.com", "magdalena", "salipur", "cimsss", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("liki", "liki@gmail.com", "lidija", "salipur", "djiki", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("kale", "kale@gmail.com", "katarina", "obrenovic", "kacakaca", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("bole", "bole@gmail.com", "bosko", "prezime", "pomazebog", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("sanja", "sanja@gmail.com", "sanja", "milojevic", "cikepike", 3);


insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sake", "address", 1, 2,"sake96", "address2", 1, "sakesake", 1);
insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sadsa", "sdaasdas", 15, 26,"sadsasake96", "adsdadadress2", 13, "sakdsaesake", 1);

insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("neki", "address", 1, 2,"neki96", "address2", 1, "neki", 2);
insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("nekica", "sdaasdas", 15, 26,"nelica96", "adsdadadress2", 13, "nelica", 2);


insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "prvi mejl", '2018-12-12',"sandra", "prvi naslov", "kome meni", true,1, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "drugi mejl", '2018-12-12',"nela", "drugi naslov", "kome meni", true,1, null);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "treci mejl", '2018-12-12',"kaja", "treci naslov", "kome meni", true,2, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "cetvrti mejl", '2018-12-12',"kaca", "4 naslov", "kome meni", true,2, null);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "prvi mejl", '2018-12-12',"sandra", "hhh", "kome meni", true,3, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "drugi mejl", '2018-12-12',"nela", "phhh", "kome meni", true,3, null);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "treci mejl", '2003-12-12',"kaja", "ss", "kome meni", true,4, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "cetvrti mejl", '2017-12-12',"kaca", "4 naslov", "kome meni", true,4, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("", "", "proba mejl", '2017-12-12',"kaca", "prvi naslov", "kome meni", true,4, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("", "", "druga proba mejl", '2015-12-12',"mici", "naslovcic", "sandra", true,3, null);

insert into tag(tag_name,user_id) values ("important", 1);
insert into tag(tag_name, user_id) values ("urgent",1);
insert into tag(tag_name, user_id) values ("primary",1);

insert into message_tag(message_id, tag_id) values(1,1);
insert into message_tag(message_id, tag_id) values(1,2);
insert into message_tag(message_id, tag_id) values(2,2)
insert into message_tag(message_id, tag_id) values(2,3);
insert into message_tag(message_id, tag_id) values(1,4);
insert into message_tag(message_id, tag_id) values(2,4);

