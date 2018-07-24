/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.refarch.cqrs.trader.query.company;

import io.pivotal.refarch.cqrs.trader.coreapi.company.CompanyCreatedEvent;
import io.pivotal.refarch.cqrs.trader.coreapi.company.CompanyView;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventHandler {

    private final CompanyViewRepository companyRepository;

    public CompanyEventHandler(CompanyViewRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @EventHandler
    public void on(CompanyCreatedEvent event) {
        CompanyView companyView = new CompanyView();

        companyView.setIdentifier(event.getCompanyId().getIdentifier());
        companyView.setValue(event.getCompanyValue());
        companyView.setAmountOfShares(event.getAmountOfShares());
        companyView.setTradeStarted(true);
        companyView.setName(event.getCompanyName());

        companyRepository.save(companyView);
    }
}
