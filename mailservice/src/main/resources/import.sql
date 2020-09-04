insert into euser (user_firstname, user_lastname, user_password, user_username, user_type) values ("sandra", "stojanovic", "s", "sakica", "ADMIN");
insert into euser (user_firstname, user_lastname, user_password, user_username, user_type) values ("nela", "milojevic", "n", "nekica", "ADMIN");
insert into euser (user_firstname, user_lastname, user_password, user_username, user_type) values ("marko", "cvijovic", "m", "make", "USER");
insert into euser (user_firstname, user_lastname, user_password, user_username, user_type) values ("nikola", "spasojevic", "sake", "sake", "USER");


insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("megs", "megs@gmail.com", "magdalena", "salipur", "cimsss", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("liki", "liki@gmail.com", "lidija", "salipur", "djiki", 1);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("kale", "kale@gmail.com", "katarina", "obrenovic", "kacakaca", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("bole", "bole@gmail.com", "bosko", "prezime", "pomazebog", 2);
insert into contact (contact_display_name, contact_email, contact_firstname, contact_lastname, contact_note, user_id) values ("sanja", "sanja@gmail.com", "sanja", "milojevic", "cikepike", 3);


insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sake", "address", 1, 2,"sake96", "address2", 1, "sakesake", 1);
insert into accounts (display_name, in_server_address, in_server_port, in_server_type, account_password, smtp_address, smtp_port, account_username, user_id) values ("sadsa", "sdaasdas", 15, 26,"sadsasake96", "adsdadadress2", 13, "sakdsaesake", 1);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "prvi mejl", '2018-12-12',"sandra", "prvi naslov", "kome meni", true,1, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "drugi mejl", '2018-12-12',"nela", "drugi naslov", "kome meni", true,1, null);

insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "treci mejl", '2018-12-12',"kaja", "treci naslov", "kome meni", true,2, null);
insert into message (bcc_message, cc_message, content, date_time, from_message, subject_mess, to_message, is_unread, account_id, folder_id) values ("bbc", "cc", "cetvrti mejl", '2018-12-12',"kaca", "4 naslov", "kome meni", true,2, null);
