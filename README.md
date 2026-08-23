# petriDishSimulator

** This project is a Java application packaged and run as a Docker image (shimm0/petridishsimulator). It consists of a Boids-based simulator of flocking creatures, inspired by organisms found in a Petri dish.

## Table of Contents

- [Project Structure](#project-structure)
- [Installation](#installation)

## Project Structure

```text
.
├── Dockerfile # To deploy on Docker
├── LICENSE
├── README.md
├── bin
│   ├── icons
│   └── simulator
├── pom.xml # To package using Maven inside the container
├── resources # Project resources
│   └── icons
└── src # Main project code
    └── simulator
```


## Installation
# Windows

1. Install VcXsrv from this [link](https://vcxsrv.com/) using the step-by-step installation instructions provided (GitHub-> download from Releases)

2. Install WSL in Powershell and check that the version is 2
    ```bash
    wsl --install
    wsl.exe -l -v
    ```
    If the version is 1, change it to WSL 2
    ```bash
    wsl --set-version Ubuntu 2
    ```
3. Install Docker in WSL
    ```bash
    sudo apt update
    sudo apt install snapd
    sudo snap install docker
    docker --version
    docker run hello-world
    ```
4. Launch XLaunch on Windows, selecting the options Multiple windows with display number 0, Start no client and Disable access control

5. Retrieve the Window host's IP address within WSL
    ```bash
    export DISPLAY=$(ip route | awk '/default/ {print $3}'):0.0
    echo $DISPLAY
    ```
6. Check if the X server is working (a pop-up window should appear)
    ```bash
    sudo apt install x11-apps
    xclock
    ```
7. Run the Docker container
    ```bash
    docker run -it --rm \
        --network=host \
        -e DISPLAY="$DISPLAY" \
        shimm0/petridishsimulator
    ```

