package com.example.connector;

import org.apache.kafka.common.cache.Cache;
import org.apache.kafka.common.cache.LRUCache;
import org.apache.kafka.common.cache.SynchronizedCache;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;

import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.transforms.util.SimpleConfig;

import java.util.Map;

import com.example.connector.utils.GenericAvroUtil;
import com.example.connector.dtos.GenericAvroDto;
import com.example.connector.GenericAvroTransformation;

public class Value<R extends ConnectRecord<R>> extends GenericAvroTransformation<R> {
    @Override
    protected Schema operatingSchema(R record) {
        return record.valueSchema();
    }
    @Override
    protected Object operatingValue(R record) {
        return record.value();
    }
    @Override
    protected R newRecord(R record, Schema updatedSchema, Object updatedValue) {
        return record.newRecord(record.topic(), record.kafkaPartition(), record.keySchema(), record.key(), updatedSchema, updatedValue, record.timestamp());
    }
}