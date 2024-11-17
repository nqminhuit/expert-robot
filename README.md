# Development

Start the system:
```bash
mvn install
podman kube play deploy.dev.yaml --replace --build
```

Remote debug ports:

- crawler: 5005
- processor: 5006
