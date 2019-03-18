delete from pme_preferences;

insert into pme_preferences(pref_id, name, type) values(1, 'Balanced', 'health');
insert into pme_preferences(pref_id, name, type) values(2, 'High-Fiber', 'health');
insert into pme_preferences(pref_id, name, type) values(3, 'High-Protein', 'health');
insert into pme_preferences(pref_id, name, type) values(4, 'Low-Carb', 'health');
insert into pme_preferences(pref_id, name, type) values(5, 'Low-Fat', 'health');
insert into pme_preferences(pref_id, name, type) values(6, 'Low-Sodium', 'health');
insert into pme_preferences(pref_id, name, type) values(7, 'Dairy-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(8, 'Egg-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(9, 'Fish-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(10, 'Gluten-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(11, 'Low-Potassium', 'health');
insert into pme_preferences(pref_id, name, type) values(12, 'Low-Sugar', 'health');
insert into pme_preferences(pref_id, name, type) values(13, 'Peanut-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(14, 'Pescatarian', 'health');
insert into pme_preferences(pref_id, name, type) values(15, 'Pork-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(16, 'Red-Meat-Free', 'health');
insert into pme_preferences(pref_id, name, type) values(17, 'Vegan', 'health');
insert into pme_preferences(pref_id, name, type) values(18, 'Vegatarian', 'health');
insert into pme_preferences(pref_id, name, type) values(19, 'Wheat-free', 'health');
insert into pme_preferences(pref_id, name, type) values(20, 'Chinese', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(21, 'Mexican', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(22, 'Italian', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(23, 'Japanese', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(24, 'Greek', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(25, 'French', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(26, 'Thai', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(27, 'Spanish', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(28, 'Indian', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(29, 'Mediterranean', 'cuisine');
insert into pme_preferences(pref_id, name, type) values(30, 'Malaysian', 'cuisine');

select * from pme_preferences;

select pme_pref_seq.currval from dual;
select pme_pref_seq.nextval from dual;

commit;