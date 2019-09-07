INSERT INTO bib_users (name, email, password, enabled)
  values
  ('Admin Meier', 'admin@email.com', 'admin', 1),
  ('User Meier', 'user@email.com', 'admin', 1),
  ('Markus Meier', 'markus.meier@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1),
  ('Klaus Fichte', 'klaus.fichte@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1),
  ('Denise Richards', 'denise.richards@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1);

INSERT INTO authorities (email, authority)
  values
  ('admin@email.com', 'ROLE_ADMIN'),
  ('markus.meier@email.com', 'ROLE_USER'),
  ('klaus.fichte@email.com', 'ROLE_USER');