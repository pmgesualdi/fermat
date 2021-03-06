package com.bitdubai.fermat_csh_plugin.layer.wallet.cash_money.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.CantStartPluginException;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.enums.FiatCurrency;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantChangeCashMoneyWalletException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantCreateCashMoneyException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantLoadCashMoneyException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.interfaces.CashMoneyWallet;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.interfaces.CashMoneyWalletManager;
import com.bitdubai.fermat_csh_plugin.layer.wallet.cash_money.developer.bitdubai.version_1.database.CashMoneyWalletDao;
import com.bitdubai.fermat_csh_plugin.layer.wallet.cash_money.developer.bitdubai.version_1.exceptions.CantInitializeCashMoneyWalletDatabaseException;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.UnexpectedPluginExceptionSeverity;

import java.util.UUID;

/**
 * Created by Alejandro Bicelis on 11/23/2015.
 */
public class CashMoneyWalletManagerImpl implements CashMoneyWalletManager {

    private final PluginDatabaseSystem pluginDatabaseSystem;
    private final UUID pluginId;
    private final ErrorManager errorManager;

    private CashMoneyWalletDao dao;
    private CashMoneyWalletImpl cashMoneyWalletImpl;


    public CashMoneyWalletManagerImpl(final PluginDatabaseSystem pluginDatabaseSystem, final UUID pluginId, final ErrorManager errorManager) throws CantStartPluginException {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.pluginId = pluginId;
        this.errorManager = errorManager;

        try {
            this.dao = new CashMoneyWalletDao(pluginDatabaseSystem, pluginId, errorManager);
            dao.initialize();

            this.cashMoneyWalletImpl = new CashMoneyWalletImpl(pluginDatabaseSystem, pluginId, errorManager);
        } catch (Exception e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_CSH_MONEY_TRANSACTION_HOLD, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, e);
            throw new CantStartPluginException(CantStartPluginException.DEFAULT_MESSAGE, FermatException.wrapException(e), null, null);
        }
    }



    @Override
    public CashMoneyWallet loadCashMoneyWallet(String walletPublicKey) throws CantLoadCashMoneyException {
        try{
            cashMoneyWalletImpl.changeWalletTo(walletPublicKey);
            return cashMoneyWalletImpl;
        } catch (CantChangeCashMoneyWalletException e) {
            throw new CantLoadCashMoneyException(CantLoadCashMoneyException.DEFAULT_MESSAGE, e, null, null);
        }
    }

    @Override
    public void createCashMoney(String walletPublicKey, FiatCurrency fiatCurrency) throws CantCreateCashMoneyException {
        try{
            //dao.createCashMoneyWallet(walletPublicKey, FiatCurrency fiatCurrency);
        }catch(Exception e)
        {

        }
    }
}
