alter table rants add constraint visitors_fkey foreign key (visitorId) references visitors (id) match full;