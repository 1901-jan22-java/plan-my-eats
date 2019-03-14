delete from pme_preferences;

insert into pme_preferences(pref_id, name) values(1, 'Balanced');
insert into pme_preferences(pref_id, name) values(2, 'High-Fiber');
insert into pme_preferences(pref_id, name) values(3, 'High-Protein');
insert into pme_preferences(pref_id, name) values(4, 'Low-Carb');
insert into pme_preferences(pref_id, name) values(5, 'Low-Fat');
insert into pme_preferences(pref_id, name) values(6, 'Low-Sodium');
insert into pme_preferences(pref_id, name) values(7, 'Dairy-Free');
insert into pme_preferences(pref_id, name) values(8, 'Egg-Free');
insert into pme_preferences(pref_id, name) values(9, 'Fish-Free');
insert into pme_preferences(pref_id, name) values(10, 'Gluten-Free');
insert into pme_preferences(pref_id, name) values(11, 'Low-Potassium');
insert into pme_preferences(pref_id, name) values(12, 'Low-Sugar');
insert into pme_preferences(pref_id, name) values(13, 'Peanut-Free');
insert into pme_preferences(pref_id, name) values(14, 'Pescatarian');
insert into pme_preferences(pref_id, name) values(15, 'Pork-Free');
insert into pme_preferences(pref_id, name) values(16, 'Red-Meat-Free');
insert into pme_preferences(pref_id, name) values(17, 'Vegan');
insert into pme_preferences(pref_id, name) values(18, 'Vegatarian');
insert into pme_preferences(pref_id, name) values(19, 'Wheat-free');
insert into pme_preferences(pref_id, name) values(20, 'Chinese');
insert into pme_preferences(pref_id, name) values(21, 'Mexican');
insert into pme_preferences(pref_id, name) values(22, 'Italian');
insert into pme_preferences(pref_id, name) values(23, 'Japanese');
insert into pme_preferences(pref_id, name) values(24, 'Greek');
insert into pme_preferences(pref_id, name) values(25, 'French');
insert into pme_preferences(pref_id, name) values(26, 'Thai');
insert into pme_preferences(pref_id, name) values(27, 'Spanish');
insert into pme_preferences(pref_id, name) values(28, 'Indian');
insert into pme_preferences(pref_id, name) values(29, 'Mediterranean');
insert into pme_preferences(pref_id, name) values(30, 'Malaysian');

select * from pme_preferences;

select pme_pref_seq.currval from dual;
select pme_pref_seq.nextval from dual;

commit;