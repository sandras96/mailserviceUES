insert into euser (user_firstname, user_lastname, user_password, user_username) values ("sandra", "stojanovic", "s", "sakica");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("nela", "milojevic", "n", "nekica");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("marko", "cvijovic", "m", "make");
insert into euser (user_firstname, user_lastname, user_password, user_username) values ("nikola", "spasojevic", "sake", "sake");


insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("megs", "megs@gmail.com", "magdalena", "salipur", "cimsss", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("liki", "liki@gmail.com", "lidija", "salipur", "djiki", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("kale", "kale@gmail.com", "katarina", "obrenovic", "kacakaca", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("bole", "bole@gmail.com", "bosko", "prezime", "pomazebog", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("kale", "kale@gmail.com", "katarina", "obrenovic", "kacakaca", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("bole", "bole@gmail.com", "bosko", "prezime", "pomazebog", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("liki", "liki@gmail.com", "lidija", "salipur", "djiki", 4);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("liki", "liki@gmail.com", "lidija", "salipur", "djiki", 3);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("megs", "megs@gmail.com", "magdalena", "salipur", "cimsss", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("megs", "megs@gmail.com", "magdalena", "salipur", "cimsss", 3);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("sanja", "sanja@gmail.com", "sanja", "milojevic", "cikepike", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, text, user_id) values ("sanja", "sanja@gmail.com", "sanja", "milojevic", "cikepike", 2);


insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sake", "address", 1, 2,"sake96", "address2", 1, "sakesake", 1);
insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sadsa", "sdaasdas", 15, 26,"sadsasake96", "adsdadadress2", 13, "sakdsaesake", 1);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "prvi mejl", '2018-12-12',"sandra", "prvi naslov", "kome meni", true,1, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "drugi mejl", '2018-12-12',"sandra", "drugi naslov", "kome meni", true,1, null);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "treci mejl", '2018-12-12',"sandra", "treci naslov", "kome meni", true,2, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "cetvrti mejl", '2018-12-12',"sandra", "4 naslov", "kome meni", true,2, null);
