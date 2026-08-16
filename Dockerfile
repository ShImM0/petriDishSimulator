# Base maven image, 3.6.3 is a version where the maven plugins work, with Java 17
FROM maven:3.6.3-openjdk-17

# For GUI dependencies (using the package manager)
RUN microdnf install -y \
        libXext \
        libXrender \
        libXtst \
        libXi \
        libX11 \
        libxcb \
    && microdnf clean all

WORKDIR /apps
COPY . /apps

# Compile each module and clear compile files
RUN mvn clean install

# Runs the file that contains the main method without building a jar
CMD ["mvn", "exec:java", "-Dexec.mainClass=simulator.launcher.Main"]
