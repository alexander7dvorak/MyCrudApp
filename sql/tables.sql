create table manufacturers (
	id  int(8) PRIMARY KEY AUTO_INCREMENT,
	name varchar(220) NOT NULL
);

insert into manufacturers(id,name)
values ROW(1,'Ford Motor Company'), ROW(2,'General Motors Company'), ROW(3,'Toyota Motor Corporation'),
ROW(4,'Stellantis NV'), ROW(5,'Honda Motor Co. Ltd.');



create table engines (
	id  int(8) PRIMARY KEY AUTO_INCREMENT,
	manufacturer_id  int(8) NOT NULL,
	name varchar(220) NOT NULL,
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id) ON DELETE NO ACTION
);

insert into engines(id, manufacturer_id, name)
values ROW(1, 1, '1.0 L Fox Ti-VCT I3'), ROW(2, 1, '1.1 L Duratec Ti-VCT I3'), ROW(3, 1, '1.2 L Dragon Ti-VCT I3'),
ROW(4, 1, '1.5 L Dragon Ti-VCT I3'), ROW(5, 2, '1.0 Liter I3 Ecotec LDB'),
ROW(6, 2, '1.2 Liter Ecotec I4 LL0'), ROW(7, 2, '1.0 Liter I3 Ecotec LDB'),
ROW(8, 2, '1.5 L Dragon Ti-VCT I3'), ROW(9, 2, '1.2 Liter I4 Ecotec LDC'),
ROW(10, 2, '1.2 Liter I4 Ecotec LWD'), ROW(11, 2, '1.2 Liter Turbo I-3 LIH'),
ROW(12, 2, '1.3 Liter Turbo I-3 L3T'), ROW(13, 2, '1.4 Liter I4 Ecotec L2Z'),
ROW(14, 3, '2.4 L T24A'), ROW(15, 3, '3.0 L (2997 cc) 2JZ'),
ROW(16, 3, '4.5 L (4477 cc) 1FZ'), ROW(17, 3, '2.5 L (2497 cc) 5GR'),
ROW(18, 4, '6.4-liter HEMI® V-8 engine in the 2021 Jeep® Wrangler Rubicon 392'), ROW(19, 4, '6.4-liter HEMI® V-8 engine in the 2021 Jeep® Wrangler Rubicon 392'),
ROW(20, 4, '2021 Jeep® Wrangler Rubicon 6.4-liter HEMI® V-8'), ROW(21, 4, '2021 Jeep® Wrangler Rubicon 392 isolated engine'),
ROW(22, 5, '2.0 L R20A1 (Acura ILX) i-VTEC'), ROW(23, 5, '3.5 L HI7R (Dallara IR-05)');


create table cars(
	id  int(8) PRIMARY KEY AUTO_INCREMENT,
	manufacturer_id  int(8) NOT NULL,
	engine_id int(8) NOT NULL,
	name varchar(220) NOT NULL,
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id) ON DELETE NO ACTION,
	FOREIGN KEY (engine_id) REFERENCES engines(id) ON DELETE CASCADE
);

insert into cars(id, manufacturer_id, engine_id, name)
values ROW(1, 1, 14, 'Some car'), ROW(2, 1, 9, 'Another car'), ROW(3, 1, 16, 'Third car'),
ROW(4, 3, 20, 'Batman car'), ROW(5, 2, 15, 'Ice cream van');
