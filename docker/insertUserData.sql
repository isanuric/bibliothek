INSERT INTO bib_users (userId, userName, email, password, enabled)
  values
  ('1000', 'admin', 'admin@email.com', 'pass', 1),
  ('1001', 'user', 'user@email.com', 'pass', 1);
--   ('Markus Meier', 'markus.meier@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1),
--   ('Klaus Fichte', 'klaus.fichte@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1),
--   ('Denise Richards', 'denise.richards@email.com', 'UnVuG9HHgffUDAlk8qfOu', 1);

INSERT INTO authorities (userName, authority)
  values
  ('admin', 'ROLE_ADMIN'),
  ('user', 'ROLE_USER');