# Development

Start the system:
```bash
mvn package
podman kube play deploy.dev.yaml --replace
```

Remote debug ports:

- crawler: 5005
- processor: 5006
