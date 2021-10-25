package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class AwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        //Comando para criação do CDK
        VpcStack vpcStack = new VpcStack(app, "Vpc01");

        //Comando para criação do Cluster
        ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        //Criação do LoadBalancer e AutoScaling
        Service01Stack service01Stack = new Service01Stack(app, "Service01", clusterStack.getCluster());
        service01Stack.addDependency(clusterStack);
        app.synth();

    }
}
