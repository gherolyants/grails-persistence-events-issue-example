package test

import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEventListener
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import org.springframework.context.ApplicationEvent

class PersistenceEventListener extends AbstractPersistenceEventListener {

    protected PersistenceEventListener(Datastore datastore) {
        super(datastore)
    }

    @Override
    protected void onPersistenceEvent(AbstractPersistenceEvent event) {
        event.entityObject."lastUpdatedBy" = '42';
    }

    @Override
    boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        [PreInsertEvent, PreUpdateEvent].contains(eventType)
    }
}
