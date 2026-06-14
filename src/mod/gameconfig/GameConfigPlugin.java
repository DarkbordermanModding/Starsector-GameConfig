package mod.gameconfig;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionSpecAPI;
import org.json.JSONException;
import org.json.JSONObject;


public class GameConfigPlugin extends BaseModPlugin {

    public void onGameLoad(boolean newGame) {
        for (FactionSpecAPI faction : Global.getSettings().getAllFactionSpecs()) {
            try {
                JSONObject custom = faction.getCustom();
                if (custom == null) continue;
                // Not care about atrocities
                custom.put("caresAboutAtrocities", false);
                // Allow use AI core to increase relations
                if (!custom.optBoolean("buysAICores", false)) {
                    custom.put("buysAICores", true);
                    custom.put("AICoreValueMult", 0.5);
                    custom.put("AICoreRepMult", 2.0);
                }
                faction.setCustom(custom);
            } catch (JSONException e) {
                Global.getLogger(GameConfigPlugin.class).warn("Failed to set caresAboutAtrocities for faction: " + faction.getId(), e);
            }
        }
    }
}
