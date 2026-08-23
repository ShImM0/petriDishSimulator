# petriDishSimulator

## Table of Contents

## Installation
# Windows

1. Install VcXsrv from this [link](https://vcxsrv.com/) using the step-by-step installation instructions provided

2. Install WSL in Powershell
    ```bash
    wsl --install
    ```
3. Install docker in WSL
    ```bash
    sudo snap install docker
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
    docker run -it \
        --network=host \
        -e DISPLAY="$DISPLAY" \
        shimm0/petridishsimulator
    ```

