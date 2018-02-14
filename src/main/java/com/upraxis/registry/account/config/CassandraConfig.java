/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upraxis.registry.account.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.cassandra.config.DataCenterReplication;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

/**
 *
 * @author jesse
 */
@Configuration
@PropertySource(value = "classpath:conf/cassandra-config.properties")
public class CassandraConfig extends AbstractCassandraConfiguration{
    
    @Value("${cassandra.keyspace}")
    private static String KEYSPACE;
    
    @Value("${cassandra.username}")
    private static String USERNAME;
    
    @Value("${cassandra.password}")
    private static String PASSWORD;
    
    @Value("${cassandra.nodes}")
    private static String NODES;

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.upraxis.registry.account.web.repository"};
    }
    
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
        createKeyspaceSpecifications.add(getKeySpaceSpecification());
        return createKeyspaceSpecifications;
    }

    // Below method creates "my_keyspace" if it doesnt exist.
    private CreateKeyspaceSpecification getKeySpaceSpecification() {
        CreateKeyspaceSpecification pandaCoopKeyspace = new CreateKeyspaceSpecification();
        DataCenterReplication dcr = new DataCenterReplication("dc1", 3L);
        pandaCoopKeyspace.name(KEYSPACE);
        pandaCoopKeyspace.ifNotExists(true).createKeyspace().withNetworkReplication(dcr);
        return pandaCoopKeyspace;
    }
    
    @Bean
    @Override
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setContactPoints(NODES);
        bean.setUsername(USERNAME);
        bean.setPassword(PASSWORD);
        return bean;
    }
    
}
