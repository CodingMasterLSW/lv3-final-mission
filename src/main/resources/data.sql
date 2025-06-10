INSERT INTO CREW(name, email) VALUES('젠슨','a@com');
INSERT INTO CREW(name, email) VALUES('포포','b@com');
INSERT INTO CREW(name, email) VALUES('가이온','c@com');
INSERT INTO CREW(name, email) VALUES('짱구','d@com');
INSERT INTO CREW(name, email) VALUES('돔푸','e@com');

INSERT INTO COACH(name, email) VALUES('솔라','a1@com');
INSERT INTO COACH(name, email) VALUES('리사','a2@com');
INSERT INTO COACH(name, email) VALUES('네오','a3@com');

INSERT INTO RESERVATION(coach_id, crew_id, reservation_time)
VALUES(1L, 1L, '2030-06-10 10:30:00');
INSERT INTO RESERVATION(coach_id, crew_id, reservation_time)
VALUES(1L, 2L, '2030-06-11 10:30:00');
INSERT INTO RESERVATION(coach_id, crew_id, reservation_time)
VALUES(1L, 3L, '2030-06-12 10:30:00')
