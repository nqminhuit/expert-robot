# Development

Start the system:
```bash
mvn package
podman kube play --replace --build deploy.dev.yaml
```

Remote debug ports:

- crawler: 5005
- processor: 5006

Database:
```bash
podman exec -it art-db psql -U dev art

# list tables
\dt

# what db is connected
\c

select distinct year from nypost_sitemap order by year;
```
