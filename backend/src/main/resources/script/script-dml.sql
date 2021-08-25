INSERT INTO public.printerattr ("name","type") VALUES
('copies','integer'),
('mediaSize','string'),
('orientation','string'),
('sides','string'),
('color','boolean');

insert into public.reporttemplate ("name","description","filepath") values
('copia_cliente', 'copia per il cliente', 'copyclient_template.html'),
('copia_bar', 'copia per bar', 'bar_template.html'),
('copia_cucina', 'copia per la cucina', 'kitchen_template.html');