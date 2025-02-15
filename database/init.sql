create table if not exists  sales_managers (
    id serial primary key not null,
    name varchar(250) not null,
    languages varchar(100)[],
    products varchar(100)[],
    customer_ratings varchar(100)[]
);

create table if not exists slots (
    id serial primary key not null,
    start_date timestamptz not null,
    end_date timestamptz not null,
    booked boolean not null default false,
    sales_manager_id int not null references sales_managers(Id)
);

CREATE INDEX idx_slot_start_date ON slots (start_date);
CREATE INDEX idx_slot_end_date ON slots (end_date);
CREATE INDEX idx_slot_booked ON slots (booked);

/*
	Use the below view to simplify your Java codebase and map it to a Immutable JPA Entity
	If the database schema changes frequently, as maintaining views can become cumbersome.
*/

/*
CREATE OR REPLACE VIEW available_slots AS
SELECT s.id AS slot_id,
       s.start_date,
       s.end_date,
       s.booked,
       sm.id AS sales_manager_id,
       sm.name AS sales_manager_name,
       sm.languages,
       sm.products,
       sm.customer_ratings
FROM slots s
JOIN sales_managers sm ON s.sales_manager_id = sm.id
WHERE s.booked = FALSE
  AND 'German' = ANY(sm.languages)
  AND 'Bronze' = ANY(sm.customer_ratings)
  AND sm.products @> ARRAY['SolarPanels']::VARCHAR[]
  AND NOT EXISTS (
      SELECT 1
      FROM slots s2
      WHERE s2.sales_manager_id = s.sales_manager_id
        AND s2.booked = TRUE
        AND (
            (s2.start_date < s.start_date AND s2.end_date > s.start_date) OR
            (s2.start_date < s.end_date AND s2.end_date > s.end_date) OR
            (s2.start_date >= s.start_date AND s2.end_date <= s.end_date)
        )
  );
*/

insert into sales_managers (name, languages, products, customer_ratings) values ('Seller 1', '{"German"}', '{"SolarPanels"}', '{"Bronze"}');
insert into sales_managers (name, languages, products, customer_ratings) values ('Seller 2', '{"German", "English"}', '{"SolarPanels", "Heatpumps"}', '{"Gold","Silver","Bronze"}');
insert into sales_managers (name, languages, products, customer_ratings) values ('Seller 3', '{"German", "English"}', '{"Heatpumps"}', '{"Gold","Silver","Bronze"}');

insert into slots (sales_manager_id, booked, start_date, end_date) values (1, false, '2024-05-03T10:30Z', '2024-05-03T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (1, true,  '2024-05-03T11:00Z', '2024-05-03T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (1, false, '2024-05-03T11:30Z', '2024-05-03T12:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, false, '2024-05-03T10:30Z', '2024-05-03T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, false, '2024-05-03T11:00Z', '2024-05-03T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, false, '2024-05-03T11:30Z', '2024-05-03T12:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, true,  '2024-05-03T10:30Z', '2024-05-03T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, false, '2024-05-03T11:00Z', '2024-05-03T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, false, '2024-05-03T11:30Z', '2024-05-03T12:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (1, false, '2024-05-04T10:30Z', '2024-05-04T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (1, false, '2024-05-04T11:00Z', '2024-05-04T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (1, true,  '2024-05-04T11:30Z', '2024-05-04T12:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, true,  '2024-05-04T10:30Z', '2024-05-04T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, false, '2024-05-04T11:00Z', '2024-05-04T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (2, true,  '2024-05-04T11:30Z', '2024-05-04T12:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, true,  '2024-05-04T10:30Z', '2024-05-04T11:30Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, false, '2024-05-04T11:00Z', '2024-05-04T12:00Z');
insert into slots (sales_manager_id, booked, start_date, end_date) values (3, false, '2024-05-04T11:30Z', '2024-05-04T12:30Z');
