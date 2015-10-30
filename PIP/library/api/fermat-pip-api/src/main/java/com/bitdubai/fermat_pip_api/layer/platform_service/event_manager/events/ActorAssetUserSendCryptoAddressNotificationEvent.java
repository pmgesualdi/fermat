package com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.events;

import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUser;
import com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.enums.EventType;

/**
 * Created by Nerio on 27/10/15.
 */
public class ActorAssetUserSendCryptoAddressNotificationEvent extends AbstractFermatEvent {

    private ActorAssetUser actorAssetUser;

    public ActorAssetUser getActorAssetUser() {
        return actorAssetUser;
    }

    public void setActorAssetUser(ActorAssetUser actorAssetUser) {
        this.actorAssetUser = actorAssetUser;
    }

    public ActorAssetUserSendCryptoAddressNotificationEvent(EventType eventType) {
        super(eventType);
    }
}