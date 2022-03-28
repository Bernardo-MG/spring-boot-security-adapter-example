/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.security.ws.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.security.extractor.DefaultModelExtractor;
import com.bernardomg.example.security.extractor.EntitySaver;
import com.bernardomg.example.security.extractor.ModelExtractor;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;
import com.bernardomg.example.security.extractor.factory.DefaultEntitySourceFactory;
import com.bernardomg.example.security.extractor.factory.EntitySourceBuilder;
import com.bernardomg.example.security.extractor.factory.EntitySourceFactory;
import com.bernardomg.example.security.ws.adapter.service.AdapterLoaderService;
import com.bernardomg.example.security.ws.adapter.service.DefaultAdapterLoaderService;

@Configuration
public class AdapterConfiguration {

    public AdapterConfiguration() {
        super();
    }

    @Bean("adapterLoaderService")
    public AdapterLoaderService getAdapterLoaderService(
            final ModelExtractor extractor,
            final ModelExtractorConfiguration config) {
        return new DefaultAdapterLoaderService(extractor, config);
    }

    @Bean("modelExtractor")
    public ModelExtractor getModelExtractor(final EntitySourceFactory factory,
            final Collection<EntitySaver<?>> savers) {
        return new DefaultModelExtractor(factory, savers);
    }

    @Bean("entitySourceFactory")
    public EntitySourceFactory getEntitySourceFactory(
            final Collection<EntitySourceBuilder> builders) {
        return new DefaultEntitySourceFactory(builders);
    }

}
