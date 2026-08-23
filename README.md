# petriDishSimulator

## Table of Contents

## Installation
# Windows

1. Install VcXsrv from this [link](https://vcxsrv.com/) using the step-by-step installation instructions provided

2. Install WSL in Powershell and check that the version is the version 2
    ```bash
    wsl --install
    wsl.exe -l -v
    ```
    If the version is 1
    ```bash
    wsl --set-version Ubuntu 2
    ```
3. Install docker in WSL
    ```bash
    sudo apt update
    sudo apt install snapd
    sudo snap install docker
    docker --version
    docker run hello-world
    ```
4. Launch XLaunch on Windows, selecting the options Multiple windows with display number 0, Start no client and Disable access control

5. Retrieve the Window host's ip address within WSL
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

