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

select count(1) from nypost_sitemap ;
```

develop on a container:
```bash
n=crawler; cd $n; mvn package; podman build -t $n .; podman run --replace --pod art --name art-$n $n;
n=processor; cd $n; mvn package; podman build -t $n .; podman run --replace --pod art --name art-$n $n;
```
