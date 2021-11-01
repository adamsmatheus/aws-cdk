package com.myorg;

import software.amazon.awscdk.core.App;


public class AwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        //Comando para criação do CDK
        VpcStack vpcStack = new VpcStack(app, "Vpc01");

        //Comando para criação do Cluster
        ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        //Comando para criação do RDS
        RdsCdkStack rdsCdkStack = new RdsCdkStack(app, "Rds", vpcStack.getVpc());
        rdsCdkStack.addDependency(vpcStack);

        //Criação do LoadBalancer e AutoScaling
        Service01Stack service01Stack = new Service01Stack(app, "Service01", clusterStack.getCluster());
        service01Stack.addDependency(clusterStack);
        service01Stack.addDependency(rdsCdkStack);
        app.synth();

    }
}
