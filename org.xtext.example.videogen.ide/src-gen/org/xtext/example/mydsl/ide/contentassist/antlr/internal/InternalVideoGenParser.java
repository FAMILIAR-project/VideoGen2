package org.xtext.example.mydsl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.xtext.example.mydsl.services.VideoGenGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalVideoGenParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_LEFT_BRACKET", "RULE_RIGHT_BRACKET", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'TOP'", "'BOTTOM'", "'CENTER'", "'h'", "'horizontal'", "'v'", "'vertical'", "'VideoGen'", "'@author'", "'@version'", "'@creation'", "'mandatory'", "'optional'", "'alternatives'", "'image'", "'toptext'", "'bottomtext'", "'videoseq'", "'duration'", "'probability'", "'description'", "'filter'", "'text'", "'content'", "'position'", "'color'", "'size'", "'b&w'", "'negate'", "'flip'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int RULE_RIGHT_BRACKET=5;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__39=39;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=7;
    public static final int RULE_WS=11;
    public static final int RULE_LEFT_BRACKET=4;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__20=20;
    public static final int T__42=42;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalVideoGenParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalVideoGenParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalVideoGenParser.tokenNames; }
    public String getGrammarFileName() { return "InternalVideoGen.g"; }


    	private VideoGenGrammarAccess grammarAccess;

    	public void setGrammarAccess(VideoGenGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleVideoGeneratorModel"
    // InternalVideoGen.g:53:1: entryRuleVideoGeneratorModel : ruleVideoGeneratorModel EOF ;
    public final void entryRuleVideoGeneratorModel() throws RecognitionException {
        try {
            // InternalVideoGen.g:54:1: ( ruleVideoGeneratorModel EOF )
            // InternalVideoGen.g:55:1: ruleVideoGeneratorModel EOF
            {
             before(grammarAccess.getVideoGeneratorModelRule()); 
            pushFollow(FOLLOW_1);
            ruleVideoGeneratorModel();

            state._fsp--;

             after(grammarAccess.getVideoGeneratorModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVideoGeneratorModel"


    // $ANTLR start "ruleVideoGeneratorModel"
    // InternalVideoGen.g:62:1: ruleVideoGeneratorModel : ( ( rule__VideoGeneratorModel__Group__0 ) ) ;
    public final void ruleVideoGeneratorModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:66:2: ( ( ( rule__VideoGeneratorModel__Group__0 ) ) )
            // InternalVideoGen.g:67:2: ( ( rule__VideoGeneratorModel__Group__0 ) )
            {
            // InternalVideoGen.g:67:2: ( ( rule__VideoGeneratorModel__Group__0 ) )
            // InternalVideoGen.g:68:3: ( rule__VideoGeneratorModel__Group__0 )
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getGroup()); 
            // InternalVideoGen.g:69:3: ( rule__VideoGeneratorModel__Group__0 )
            // InternalVideoGen.g:69:4: rule__VideoGeneratorModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVideoGeneratorModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVideoGeneratorModel"


    // $ANTLR start "entryRuleVideoGenInformation"
    // InternalVideoGen.g:78:1: entryRuleVideoGenInformation : ruleVideoGenInformation EOF ;
    public final void entryRuleVideoGenInformation() throws RecognitionException {
        try {
            // InternalVideoGen.g:79:1: ( ruleVideoGenInformation EOF )
            // InternalVideoGen.g:80:1: ruleVideoGenInformation EOF
            {
             before(grammarAccess.getVideoGenInformationRule()); 
            pushFollow(FOLLOW_1);
            ruleVideoGenInformation();

            state._fsp--;

             after(grammarAccess.getVideoGenInformationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVideoGenInformation"


    // $ANTLR start "ruleVideoGenInformation"
    // InternalVideoGen.g:87:1: ruleVideoGenInformation : ( ( rule__VideoGenInformation__Group__0 ) ) ;
    public final void ruleVideoGenInformation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:91:2: ( ( ( rule__VideoGenInformation__Group__0 ) ) )
            // InternalVideoGen.g:92:2: ( ( rule__VideoGenInformation__Group__0 ) )
            {
            // InternalVideoGen.g:92:2: ( ( rule__VideoGenInformation__Group__0 ) )
            // InternalVideoGen.g:93:3: ( rule__VideoGenInformation__Group__0 )
            {
             before(grammarAccess.getVideoGenInformationAccess().getGroup()); 
            // InternalVideoGen.g:94:3: ( rule__VideoGenInformation__Group__0 )
            // InternalVideoGen.g:94:4: rule__VideoGenInformation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVideoGenInformationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVideoGenInformation"


    // $ANTLR start "entryRuleMedia"
    // InternalVideoGen.g:103:1: entryRuleMedia : ruleMedia EOF ;
    public final void entryRuleMedia() throws RecognitionException {
        try {
            // InternalVideoGen.g:104:1: ( ruleMedia EOF )
            // InternalVideoGen.g:105:1: ruleMedia EOF
            {
             before(grammarAccess.getMediaRule()); 
            pushFollow(FOLLOW_1);
            ruleMedia();

            state._fsp--;

             after(grammarAccess.getMediaRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMedia"


    // $ANTLR start "ruleMedia"
    // InternalVideoGen.g:112:1: ruleMedia : ( ( rule__Media__Alternatives ) ) ;
    public final void ruleMedia() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:116:2: ( ( ( rule__Media__Alternatives ) ) )
            // InternalVideoGen.g:117:2: ( ( rule__Media__Alternatives ) )
            {
            // InternalVideoGen.g:117:2: ( ( rule__Media__Alternatives ) )
            // InternalVideoGen.g:118:3: ( rule__Media__Alternatives )
            {
             before(grammarAccess.getMediaAccess().getAlternatives()); 
            // InternalVideoGen.g:119:3: ( rule__Media__Alternatives )
            // InternalVideoGen.g:119:4: rule__Media__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Media__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getMediaAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMedia"


    // $ANTLR start "entryRuleMandatoryMedia"
    // InternalVideoGen.g:128:1: entryRuleMandatoryMedia : ruleMandatoryMedia EOF ;
    public final void entryRuleMandatoryMedia() throws RecognitionException {
        try {
            // InternalVideoGen.g:129:1: ( ruleMandatoryMedia EOF )
            // InternalVideoGen.g:130:1: ruleMandatoryMedia EOF
            {
             before(grammarAccess.getMandatoryMediaRule()); 
            pushFollow(FOLLOW_1);
            ruleMandatoryMedia();

            state._fsp--;

             after(grammarAccess.getMandatoryMediaRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMandatoryMedia"


    // $ANTLR start "ruleMandatoryMedia"
    // InternalVideoGen.g:137:1: ruleMandatoryMedia : ( ( rule__MandatoryMedia__Group__0 ) ) ;
    public final void ruleMandatoryMedia() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:141:2: ( ( ( rule__MandatoryMedia__Group__0 ) ) )
            // InternalVideoGen.g:142:2: ( ( rule__MandatoryMedia__Group__0 ) )
            {
            // InternalVideoGen.g:142:2: ( ( rule__MandatoryMedia__Group__0 ) )
            // InternalVideoGen.g:143:3: ( rule__MandatoryMedia__Group__0 )
            {
             before(grammarAccess.getMandatoryMediaAccess().getGroup()); 
            // InternalVideoGen.g:144:3: ( rule__MandatoryMedia__Group__0 )
            // InternalVideoGen.g:144:4: rule__MandatoryMedia__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MandatoryMedia__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMandatoryMediaAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMandatoryMedia"


    // $ANTLR start "entryRuleOptionalMedia"
    // InternalVideoGen.g:153:1: entryRuleOptionalMedia : ruleOptionalMedia EOF ;
    public final void entryRuleOptionalMedia() throws RecognitionException {
        try {
            // InternalVideoGen.g:154:1: ( ruleOptionalMedia EOF )
            // InternalVideoGen.g:155:1: ruleOptionalMedia EOF
            {
             before(grammarAccess.getOptionalMediaRule()); 
            pushFollow(FOLLOW_1);
            ruleOptionalMedia();

            state._fsp--;

             after(grammarAccess.getOptionalMediaRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOptionalMedia"


    // $ANTLR start "ruleOptionalMedia"
    // InternalVideoGen.g:162:1: ruleOptionalMedia : ( ( rule__OptionalMedia__Group__0 ) ) ;
    public final void ruleOptionalMedia() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:166:2: ( ( ( rule__OptionalMedia__Group__0 ) ) )
            // InternalVideoGen.g:167:2: ( ( rule__OptionalMedia__Group__0 ) )
            {
            // InternalVideoGen.g:167:2: ( ( rule__OptionalMedia__Group__0 ) )
            // InternalVideoGen.g:168:3: ( rule__OptionalMedia__Group__0 )
            {
             before(grammarAccess.getOptionalMediaAccess().getGroup()); 
            // InternalVideoGen.g:169:3: ( rule__OptionalMedia__Group__0 )
            // InternalVideoGen.g:169:4: rule__OptionalMedia__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OptionalMedia__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptionalMediaAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptionalMedia"


    // $ANTLR start "entryRuleAlternativesMedia"
    // InternalVideoGen.g:178:1: entryRuleAlternativesMedia : ruleAlternativesMedia EOF ;
    public final void entryRuleAlternativesMedia() throws RecognitionException {
        try {
            // InternalVideoGen.g:179:1: ( ruleAlternativesMedia EOF )
            // InternalVideoGen.g:180:1: ruleAlternativesMedia EOF
            {
             before(grammarAccess.getAlternativesMediaRule()); 
            pushFollow(FOLLOW_1);
            ruleAlternativesMedia();

            state._fsp--;

             after(grammarAccess.getAlternativesMediaRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAlternativesMedia"


    // $ANTLR start "ruleAlternativesMedia"
    // InternalVideoGen.g:187:1: ruleAlternativesMedia : ( ( rule__AlternativesMedia__Group__0 ) ) ;
    public final void ruleAlternativesMedia() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:191:2: ( ( ( rule__AlternativesMedia__Group__0 ) ) )
            // InternalVideoGen.g:192:2: ( ( rule__AlternativesMedia__Group__0 ) )
            {
            // InternalVideoGen.g:192:2: ( ( rule__AlternativesMedia__Group__0 ) )
            // InternalVideoGen.g:193:3: ( rule__AlternativesMedia__Group__0 )
            {
             before(grammarAccess.getAlternativesMediaAccess().getGroup()); 
            // InternalVideoGen.g:194:3: ( rule__AlternativesMedia__Group__0 )
            // InternalVideoGen.g:194:4: rule__AlternativesMedia__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAlternativesMediaAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAlternativesMedia"


    // $ANTLR start "entryRuleMediaDescription"
    // InternalVideoGen.g:203:1: entryRuleMediaDescription : ruleMediaDescription EOF ;
    public final void entryRuleMediaDescription() throws RecognitionException {
        try {
            // InternalVideoGen.g:204:1: ( ruleMediaDescription EOF )
            // InternalVideoGen.g:205:1: ruleMediaDescription EOF
            {
             before(grammarAccess.getMediaDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleMediaDescription();

            state._fsp--;

             after(grammarAccess.getMediaDescriptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMediaDescription"


    // $ANTLR start "ruleMediaDescription"
    // InternalVideoGen.g:212:1: ruleMediaDescription : ( ( rule__MediaDescription__Alternatives ) ) ;
    public final void ruleMediaDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:216:2: ( ( ( rule__MediaDescription__Alternatives ) ) )
            // InternalVideoGen.g:217:2: ( ( rule__MediaDescription__Alternatives ) )
            {
            // InternalVideoGen.g:217:2: ( ( rule__MediaDescription__Alternatives ) )
            // InternalVideoGen.g:218:3: ( rule__MediaDescription__Alternatives )
            {
             before(grammarAccess.getMediaDescriptionAccess().getAlternatives()); 
            // InternalVideoGen.g:219:3: ( rule__MediaDescription__Alternatives )
            // InternalVideoGen.g:219:4: rule__MediaDescription__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__MediaDescription__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getMediaDescriptionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMediaDescription"


    // $ANTLR start "entryRuleImageDescription"
    // InternalVideoGen.g:228:1: entryRuleImageDescription : ruleImageDescription EOF ;
    public final void entryRuleImageDescription() throws RecognitionException {
        try {
            // InternalVideoGen.g:229:1: ( ruleImageDescription EOF )
            // InternalVideoGen.g:230:1: ruleImageDescription EOF
            {
             before(grammarAccess.getImageDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleImageDescription();

            state._fsp--;

             after(grammarAccess.getImageDescriptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleImageDescription"


    // $ANTLR start "ruleImageDescription"
    // InternalVideoGen.g:237:1: ruleImageDescription : ( ( rule__ImageDescription__Group__0 ) ) ;
    public final void ruleImageDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:241:2: ( ( ( rule__ImageDescription__Group__0 ) ) )
            // InternalVideoGen.g:242:2: ( ( rule__ImageDescription__Group__0 ) )
            {
            // InternalVideoGen.g:242:2: ( ( rule__ImageDescription__Group__0 ) )
            // InternalVideoGen.g:243:3: ( rule__ImageDescription__Group__0 )
            {
             before(grammarAccess.getImageDescriptionAccess().getGroup()); 
            // InternalVideoGen.g:244:3: ( rule__ImageDescription__Group__0 )
            // InternalVideoGen.g:244:4: rule__ImageDescription__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleImageDescription"


    // $ANTLR start "entryRuleVideoDescription"
    // InternalVideoGen.g:253:1: entryRuleVideoDescription : ruleVideoDescription EOF ;
    public final void entryRuleVideoDescription() throws RecognitionException {
        try {
            // InternalVideoGen.g:254:1: ( ruleVideoDescription EOF )
            // InternalVideoGen.g:255:1: ruleVideoDescription EOF
            {
             before(grammarAccess.getVideoDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleVideoDescription();

            state._fsp--;

             after(grammarAccess.getVideoDescriptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVideoDescription"


    // $ANTLR start "ruleVideoDescription"
    // InternalVideoGen.g:262:1: ruleVideoDescription : ( ( rule__VideoDescription__Group__0 ) ) ;
    public final void ruleVideoDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:266:2: ( ( ( rule__VideoDescription__Group__0 ) ) )
            // InternalVideoGen.g:267:2: ( ( rule__VideoDescription__Group__0 ) )
            {
            // InternalVideoGen.g:267:2: ( ( rule__VideoDescription__Group__0 ) )
            // InternalVideoGen.g:268:3: ( rule__VideoDescription__Group__0 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup()); 
            // InternalVideoGen.g:269:3: ( rule__VideoDescription__Group__0 )
            // InternalVideoGen.g:269:4: rule__VideoDescription__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVideoDescription"


    // $ANTLR start "entryRuleVideoText"
    // InternalVideoGen.g:278:1: entryRuleVideoText : ruleVideoText EOF ;
    public final void entryRuleVideoText() throws RecognitionException {
        try {
            // InternalVideoGen.g:279:1: ( ruleVideoText EOF )
            // InternalVideoGen.g:280:1: ruleVideoText EOF
            {
             before(grammarAccess.getVideoTextRule()); 
            pushFollow(FOLLOW_1);
            ruleVideoText();

            state._fsp--;

             after(grammarAccess.getVideoTextRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVideoText"


    // $ANTLR start "ruleVideoText"
    // InternalVideoGen.g:287:1: ruleVideoText : ( ( rule__VideoText__Group__0 ) ) ;
    public final void ruleVideoText() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:291:2: ( ( ( rule__VideoText__Group__0 ) ) )
            // InternalVideoGen.g:292:2: ( ( rule__VideoText__Group__0 ) )
            {
            // InternalVideoGen.g:292:2: ( ( rule__VideoText__Group__0 ) )
            // InternalVideoGen.g:293:3: ( rule__VideoText__Group__0 )
            {
             before(grammarAccess.getVideoTextAccess().getGroup()); 
            // InternalVideoGen.g:294:3: ( rule__VideoText__Group__0 )
            // InternalVideoGen.g:294:4: rule__VideoText__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVideoTextAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVideoText"


    // $ANTLR start "entryRulePosition"
    // InternalVideoGen.g:303:1: entryRulePosition : rulePosition EOF ;
    public final void entryRulePosition() throws RecognitionException {
        try {
            // InternalVideoGen.g:304:1: ( rulePosition EOF )
            // InternalVideoGen.g:305:1: rulePosition EOF
            {
             before(grammarAccess.getPositionRule()); 
            pushFollow(FOLLOW_1);
            rulePosition();

            state._fsp--;

             after(grammarAccess.getPositionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePosition"


    // $ANTLR start "rulePosition"
    // InternalVideoGen.g:312:1: rulePosition : ( ( rule__Position__Alternatives ) ) ;
    public final void rulePosition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:316:2: ( ( ( rule__Position__Alternatives ) ) )
            // InternalVideoGen.g:317:2: ( ( rule__Position__Alternatives ) )
            {
            // InternalVideoGen.g:317:2: ( ( rule__Position__Alternatives ) )
            // InternalVideoGen.g:318:3: ( rule__Position__Alternatives )
            {
             before(grammarAccess.getPositionAccess().getAlternatives()); 
            // InternalVideoGen.g:319:3: ( rule__Position__Alternatives )
            // InternalVideoGen.g:319:4: rule__Position__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Position__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPositionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePosition"


    // $ANTLR start "entryRuleFilter"
    // InternalVideoGen.g:328:1: entryRuleFilter : ruleFilter EOF ;
    public final void entryRuleFilter() throws RecognitionException {
        try {
            // InternalVideoGen.g:329:1: ( ruleFilter EOF )
            // InternalVideoGen.g:330:1: ruleFilter EOF
            {
             before(grammarAccess.getFilterRule()); 
            pushFollow(FOLLOW_1);
            ruleFilter();

            state._fsp--;

             after(grammarAccess.getFilterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFilter"


    // $ANTLR start "ruleFilter"
    // InternalVideoGen.g:337:1: ruleFilter : ( ( rule__Filter__Alternatives ) ) ;
    public final void ruleFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:341:2: ( ( ( rule__Filter__Alternatives ) ) )
            // InternalVideoGen.g:342:2: ( ( rule__Filter__Alternatives ) )
            {
            // InternalVideoGen.g:342:2: ( ( rule__Filter__Alternatives ) )
            // InternalVideoGen.g:343:3: ( rule__Filter__Alternatives )
            {
             before(grammarAccess.getFilterAccess().getAlternatives()); 
            // InternalVideoGen.g:344:3: ( rule__Filter__Alternatives )
            // InternalVideoGen.g:344:4: rule__Filter__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Filter__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFilterAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFilter"


    // $ANTLR start "entryRuleBlackWhiteFilter"
    // InternalVideoGen.g:353:1: entryRuleBlackWhiteFilter : ruleBlackWhiteFilter EOF ;
    public final void entryRuleBlackWhiteFilter() throws RecognitionException {
        try {
            // InternalVideoGen.g:354:1: ( ruleBlackWhiteFilter EOF )
            // InternalVideoGen.g:355:1: ruleBlackWhiteFilter EOF
            {
             before(grammarAccess.getBlackWhiteFilterRule()); 
            pushFollow(FOLLOW_1);
            ruleBlackWhiteFilter();

            state._fsp--;

             after(grammarAccess.getBlackWhiteFilterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBlackWhiteFilter"


    // $ANTLR start "ruleBlackWhiteFilter"
    // InternalVideoGen.g:362:1: ruleBlackWhiteFilter : ( ( rule__BlackWhiteFilter__Group__0 ) ) ;
    public final void ruleBlackWhiteFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:366:2: ( ( ( rule__BlackWhiteFilter__Group__0 ) ) )
            // InternalVideoGen.g:367:2: ( ( rule__BlackWhiteFilter__Group__0 ) )
            {
            // InternalVideoGen.g:367:2: ( ( rule__BlackWhiteFilter__Group__0 ) )
            // InternalVideoGen.g:368:3: ( rule__BlackWhiteFilter__Group__0 )
            {
             before(grammarAccess.getBlackWhiteFilterAccess().getGroup()); 
            // InternalVideoGen.g:369:3: ( rule__BlackWhiteFilter__Group__0 )
            // InternalVideoGen.g:369:4: rule__BlackWhiteFilter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BlackWhiteFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBlackWhiteFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBlackWhiteFilter"


    // $ANTLR start "entryRuleNegateFilter"
    // InternalVideoGen.g:378:1: entryRuleNegateFilter : ruleNegateFilter EOF ;
    public final void entryRuleNegateFilter() throws RecognitionException {
        try {
            // InternalVideoGen.g:379:1: ( ruleNegateFilter EOF )
            // InternalVideoGen.g:380:1: ruleNegateFilter EOF
            {
             before(grammarAccess.getNegateFilterRule()); 
            pushFollow(FOLLOW_1);
            ruleNegateFilter();

            state._fsp--;

             after(grammarAccess.getNegateFilterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNegateFilter"


    // $ANTLR start "ruleNegateFilter"
    // InternalVideoGen.g:387:1: ruleNegateFilter : ( ( rule__NegateFilter__Group__0 ) ) ;
    public final void ruleNegateFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:391:2: ( ( ( rule__NegateFilter__Group__0 ) ) )
            // InternalVideoGen.g:392:2: ( ( rule__NegateFilter__Group__0 ) )
            {
            // InternalVideoGen.g:392:2: ( ( rule__NegateFilter__Group__0 ) )
            // InternalVideoGen.g:393:3: ( rule__NegateFilter__Group__0 )
            {
             before(grammarAccess.getNegateFilterAccess().getGroup()); 
            // InternalVideoGen.g:394:3: ( rule__NegateFilter__Group__0 )
            // InternalVideoGen.g:394:4: rule__NegateFilter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NegateFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNegateFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNegateFilter"


    // $ANTLR start "entryRuleFlipFilter"
    // InternalVideoGen.g:403:1: entryRuleFlipFilter : ruleFlipFilter EOF ;
    public final void entryRuleFlipFilter() throws RecognitionException {
        try {
            // InternalVideoGen.g:404:1: ( ruleFlipFilter EOF )
            // InternalVideoGen.g:405:1: ruleFlipFilter EOF
            {
             before(grammarAccess.getFlipFilterRule()); 
            pushFollow(FOLLOW_1);
            ruleFlipFilter();

            state._fsp--;

             after(grammarAccess.getFlipFilterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFlipFilter"


    // $ANTLR start "ruleFlipFilter"
    // InternalVideoGen.g:412:1: ruleFlipFilter : ( ( rule__FlipFilter__Group__0 ) ) ;
    public final void ruleFlipFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:416:2: ( ( ( rule__FlipFilter__Group__0 ) ) )
            // InternalVideoGen.g:417:2: ( ( rule__FlipFilter__Group__0 ) )
            {
            // InternalVideoGen.g:417:2: ( ( rule__FlipFilter__Group__0 ) )
            // InternalVideoGen.g:418:3: ( rule__FlipFilter__Group__0 )
            {
             before(grammarAccess.getFlipFilterAccess().getGroup()); 
            // InternalVideoGen.g:419:3: ( rule__FlipFilter__Group__0 )
            // InternalVideoGen.g:419:4: rule__FlipFilter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FlipFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFlipFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFlipFilter"


    // $ANTLR start "rule__Media__Alternatives"
    // InternalVideoGen.g:427:1: rule__Media__Alternatives : ( ( ruleMandatoryMedia ) | ( ruleOptionalMedia ) | ( ruleAlternativesMedia ) );
    public final void rule__Media__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:431:1: ( ( ruleMandatoryMedia ) | ( ruleOptionalMedia ) | ( ruleAlternativesMedia ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt1=1;
                }
                break;
            case 25:
                {
                alt1=2;
                }
                break;
            case 26:
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalVideoGen.g:432:2: ( ruleMandatoryMedia )
                    {
                    // InternalVideoGen.g:432:2: ( ruleMandatoryMedia )
                    // InternalVideoGen.g:433:3: ruleMandatoryMedia
                    {
                     before(grammarAccess.getMediaAccess().getMandatoryMediaParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleMandatoryMedia();

                    state._fsp--;

                     after(grammarAccess.getMediaAccess().getMandatoryMediaParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:438:2: ( ruleOptionalMedia )
                    {
                    // InternalVideoGen.g:438:2: ( ruleOptionalMedia )
                    // InternalVideoGen.g:439:3: ruleOptionalMedia
                    {
                     before(grammarAccess.getMediaAccess().getOptionalMediaParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleOptionalMedia();

                    state._fsp--;

                     after(grammarAccess.getMediaAccess().getOptionalMediaParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:444:2: ( ruleAlternativesMedia )
                    {
                    // InternalVideoGen.g:444:2: ( ruleAlternativesMedia )
                    // InternalVideoGen.g:445:3: ruleAlternativesMedia
                    {
                     before(grammarAccess.getMediaAccess().getAlternativesMediaParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleAlternativesMedia();

                    state._fsp--;

                     after(grammarAccess.getMediaAccess().getAlternativesMediaParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Media__Alternatives"


    // $ANTLR start "rule__MediaDescription__Alternatives"
    // InternalVideoGen.g:454:1: rule__MediaDescription__Alternatives : ( ( ruleImageDescription ) | ( ruleVideoDescription ) );
    public final void rule__MediaDescription__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:458:1: ( ( ruleImageDescription ) | ( ruleVideoDescription ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==27) ) {
                alt2=1;
            }
            else if ( (LA2_0==30) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalVideoGen.g:459:2: ( ruleImageDescription )
                    {
                    // InternalVideoGen.g:459:2: ( ruleImageDescription )
                    // InternalVideoGen.g:460:3: ruleImageDescription
                    {
                     before(grammarAccess.getMediaDescriptionAccess().getImageDescriptionParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleImageDescription();

                    state._fsp--;

                     after(grammarAccess.getMediaDescriptionAccess().getImageDescriptionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:465:2: ( ruleVideoDescription )
                    {
                    // InternalVideoGen.g:465:2: ( ruleVideoDescription )
                    // InternalVideoGen.g:466:3: ruleVideoDescription
                    {
                     before(grammarAccess.getMediaDescriptionAccess().getVideoDescriptionParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleVideoDescription();

                    state._fsp--;

                     after(grammarAccess.getMediaDescriptionAccess().getVideoDescriptionParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MediaDescription__Alternatives"


    // $ANTLR start "rule__Position__Alternatives"
    // InternalVideoGen.g:475:1: rule__Position__Alternatives : ( ( 'TOP' ) | ( 'BOTTOM' ) | ( 'CENTER' ) );
    public final void rule__Position__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:479:1: ( ( 'TOP' ) | ( 'BOTTOM' ) | ( 'CENTER' ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt3=1;
                }
                break;
            case 14:
                {
                alt3=2;
                }
                break;
            case 15:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalVideoGen.g:480:2: ( 'TOP' )
                    {
                    // InternalVideoGen.g:480:2: ( 'TOP' )
                    // InternalVideoGen.g:481:3: 'TOP'
                    {
                     before(grammarAccess.getPositionAccess().getTOPKeyword_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getPositionAccess().getTOPKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:486:2: ( 'BOTTOM' )
                    {
                    // InternalVideoGen.g:486:2: ( 'BOTTOM' )
                    // InternalVideoGen.g:487:3: 'BOTTOM'
                    {
                     before(grammarAccess.getPositionAccess().getBOTTOMKeyword_1()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getPositionAccess().getBOTTOMKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:492:2: ( 'CENTER' )
                    {
                    // InternalVideoGen.g:492:2: ( 'CENTER' )
                    // InternalVideoGen.g:493:3: 'CENTER'
                    {
                     before(grammarAccess.getPositionAccess().getCENTERKeyword_2()); 
                    match(input,15,FOLLOW_2); 
                     after(grammarAccess.getPositionAccess().getCENTERKeyword_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Position__Alternatives"


    // $ANTLR start "rule__Filter__Alternatives"
    // InternalVideoGen.g:502:1: rule__Filter__Alternatives : ( ( ruleFlipFilter ) | ( ruleNegateFilter ) | ( ruleBlackWhiteFilter ) );
    public final void rule__Filter__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:506:1: ( ( ruleFlipFilter ) | ( ruleNegateFilter ) | ( ruleBlackWhiteFilter ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt4=1;
                }
                break;
            case 41:
                {
                alt4=2;
                }
                break;
            case 40:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalVideoGen.g:507:2: ( ruleFlipFilter )
                    {
                    // InternalVideoGen.g:507:2: ( ruleFlipFilter )
                    // InternalVideoGen.g:508:3: ruleFlipFilter
                    {
                     before(grammarAccess.getFilterAccess().getFlipFilterParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleFlipFilter();

                    state._fsp--;

                     after(grammarAccess.getFilterAccess().getFlipFilterParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:513:2: ( ruleNegateFilter )
                    {
                    // InternalVideoGen.g:513:2: ( ruleNegateFilter )
                    // InternalVideoGen.g:514:3: ruleNegateFilter
                    {
                     before(grammarAccess.getFilterAccess().getNegateFilterParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleNegateFilter();

                    state._fsp--;

                     after(grammarAccess.getFilterAccess().getNegateFilterParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:519:2: ( ruleBlackWhiteFilter )
                    {
                    // InternalVideoGen.g:519:2: ( ruleBlackWhiteFilter )
                    // InternalVideoGen.g:520:3: ruleBlackWhiteFilter
                    {
                     before(grammarAccess.getFilterAccess().getBlackWhiteFilterParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleBlackWhiteFilter();

                    state._fsp--;

                     after(grammarAccess.getFilterAccess().getBlackWhiteFilterParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Alternatives"


    // $ANTLR start "rule__FlipFilter__OrientationAlternatives_1_0"
    // InternalVideoGen.g:529:1: rule__FlipFilter__OrientationAlternatives_1_0 : ( ( 'h' ) | ( 'horizontal' ) | ( 'v' ) | ( 'vertical' ) );
    public final void rule__FlipFilter__OrientationAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:533:1: ( ( 'h' ) | ( 'horizontal' ) | ( 'v' ) | ( 'vertical' ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt5=1;
                }
                break;
            case 17:
                {
                alt5=2;
                }
                break;
            case 18:
                {
                alt5=3;
                }
                break;
            case 19:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalVideoGen.g:534:2: ( 'h' )
                    {
                    // InternalVideoGen.g:534:2: ( 'h' )
                    // InternalVideoGen.g:535:3: 'h'
                    {
                     before(grammarAccess.getFlipFilterAccess().getOrientationHKeyword_1_0_0()); 
                    match(input,16,FOLLOW_2); 
                     after(grammarAccess.getFlipFilterAccess().getOrientationHKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:540:2: ( 'horizontal' )
                    {
                    // InternalVideoGen.g:540:2: ( 'horizontal' )
                    // InternalVideoGen.g:541:3: 'horizontal'
                    {
                     before(grammarAccess.getFlipFilterAccess().getOrientationHorizontalKeyword_1_0_1()); 
                    match(input,17,FOLLOW_2); 
                     after(grammarAccess.getFlipFilterAccess().getOrientationHorizontalKeyword_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:546:2: ( 'v' )
                    {
                    // InternalVideoGen.g:546:2: ( 'v' )
                    // InternalVideoGen.g:547:3: 'v'
                    {
                     before(grammarAccess.getFlipFilterAccess().getOrientationVKeyword_1_0_2()); 
                    match(input,18,FOLLOW_2); 
                     after(grammarAccess.getFlipFilterAccess().getOrientationVKeyword_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalVideoGen.g:552:2: ( 'vertical' )
                    {
                    // InternalVideoGen.g:552:2: ( 'vertical' )
                    // InternalVideoGen.g:553:3: 'vertical'
                    {
                     before(grammarAccess.getFlipFilterAccess().getOrientationVerticalKeyword_1_0_3()); 
                    match(input,19,FOLLOW_2); 
                     after(grammarAccess.getFlipFilterAccess().getOrientationVerticalKeyword_1_0_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__OrientationAlternatives_1_0"


    // $ANTLR start "rule__VideoGeneratorModel__Group__0"
    // InternalVideoGen.g:562:1: rule__VideoGeneratorModel__Group__0 : rule__VideoGeneratorModel__Group__0__Impl rule__VideoGeneratorModel__Group__1 ;
    public final void rule__VideoGeneratorModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:566:1: ( rule__VideoGeneratorModel__Group__0__Impl rule__VideoGeneratorModel__Group__1 )
            // InternalVideoGen.g:567:2: rule__VideoGeneratorModel__Group__0__Impl rule__VideoGeneratorModel__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__VideoGeneratorModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__0"


    // $ANTLR start "rule__VideoGeneratorModel__Group__0__Impl"
    // InternalVideoGen.g:574:1: rule__VideoGeneratorModel__Group__0__Impl : ( ( rule__VideoGeneratorModel__InformationAssignment_0 )? ) ;
    public final void rule__VideoGeneratorModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:578:1: ( ( ( rule__VideoGeneratorModel__InformationAssignment_0 )? ) )
            // InternalVideoGen.g:579:1: ( ( rule__VideoGeneratorModel__InformationAssignment_0 )? )
            {
            // InternalVideoGen.g:579:1: ( ( rule__VideoGeneratorModel__InformationAssignment_0 )? )
            // InternalVideoGen.g:580:2: ( rule__VideoGeneratorModel__InformationAssignment_0 )?
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getInformationAssignment_0()); 
            // InternalVideoGen.g:581:2: ( rule__VideoGeneratorModel__InformationAssignment_0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==21) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalVideoGen.g:581:3: rule__VideoGeneratorModel__InformationAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoGeneratorModel__InformationAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoGeneratorModelAccess().getInformationAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__0__Impl"


    // $ANTLR start "rule__VideoGeneratorModel__Group__1"
    // InternalVideoGen.g:589:1: rule__VideoGeneratorModel__Group__1 : rule__VideoGeneratorModel__Group__1__Impl rule__VideoGeneratorModel__Group__2 ;
    public final void rule__VideoGeneratorModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:593:1: ( rule__VideoGeneratorModel__Group__1__Impl rule__VideoGeneratorModel__Group__2 )
            // InternalVideoGen.g:594:2: rule__VideoGeneratorModel__Group__1__Impl rule__VideoGeneratorModel__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__VideoGeneratorModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__1"


    // $ANTLR start "rule__VideoGeneratorModel__Group__1__Impl"
    // InternalVideoGen.g:601:1: rule__VideoGeneratorModel__Group__1__Impl : ( 'VideoGen' ) ;
    public final void rule__VideoGeneratorModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:605:1: ( ( 'VideoGen' ) )
            // InternalVideoGen.g:606:1: ( 'VideoGen' )
            {
            // InternalVideoGen.g:606:1: ( 'VideoGen' )
            // InternalVideoGen.g:607:2: 'VideoGen'
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getVideoGenKeyword_1()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getVideoGeneratorModelAccess().getVideoGenKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__1__Impl"


    // $ANTLR start "rule__VideoGeneratorModel__Group__2"
    // InternalVideoGen.g:616:1: rule__VideoGeneratorModel__Group__2 : rule__VideoGeneratorModel__Group__2__Impl rule__VideoGeneratorModel__Group__3 ;
    public final void rule__VideoGeneratorModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:620:1: ( rule__VideoGeneratorModel__Group__2__Impl rule__VideoGeneratorModel__Group__3 )
            // InternalVideoGen.g:621:2: rule__VideoGeneratorModel__Group__2__Impl rule__VideoGeneratorModel__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__VideoGeneratorModel__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__2"


    // $ANTLR start "rule__VideoGeneratorModel__Group__2__Impl"
    // InternalVideoGen.g:628:1: rule__VideoGeneratorModel__Group__2__Impl : ( RULE_LEFT_BRACKET ) ;
    public final void rule__VideoGeneratorModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:632:1: ( ( RULE_LEFT_BRACKET ) )
            // InternalVideoGen.g:633:1: ( RULE_LEFT_BRACKET )
            {
            // InternalVideoGen.g:633:1: ( RULE_LEFT_BRACKET )
            // InternalVideoGen.g:634:2: RULE_LEFT_BRACKET
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getLEFT_BRACKETTerminalRuleCall_2()); 
            match(input,RULE_LEFT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoGeneratorModelAccess().getLEFT_BRACKETTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__2__Impl"


    // $ANTLR start "rule__VideoGeneratorModel__Group__3"
    // InternalVideoGen.g:643:1: rule__VideoGeneratorModel__Group__3 : rule__VideoGeneratorModel__Group__3__Impl rule__VideoGeneratorModel__Group__4 ;
    public final void rule__VideoGeneratorModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:647:1: ( rule__VideoGeneratorModel__Group__3__Impl rule__VideoGeneratorModel__Group__4 )
            // InternalVideoGen.g:648:2: rule__VideoGeneratorModel__Group__3__Impl rule__VideoGeneratorModel__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__VideoGeneratorModel__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__3"


    // $ANTLR start "rule__VideoGeneratorModel__Group__3__Impl"
    // InternalVideoGen.g:655:1: rule__VideoGeneratorModel__Group__3__Impl : ( ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) ) ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* ) ) ;
    public final void rule__VideoGeneratorModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:659:1: ( ( ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) ) ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* ) ) )
            // InternalVideoGen.g:660:1: ( ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) ) ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* ) )
            {
            // InternalVideoGen.g:660:1: ( ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) ) ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* ) )
            // InternalVideoGen.g:661:2: ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) ) ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* )
            {
            // InternalVideoGen.g:661:2: ( ( rule__VideoGeneratorModel__MediasAssignment_3 ) )
            // InternalVideoGen.g:662:3: ( rule__VideoGeneratorModel__MediasAssignment_3 )
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getMediasAssignment_3()); 
            // InternalVideoGen.g:663:3: ( rule__VideoGeneratorModel__MediasAssignment_3 )
            // InternalVideoGen.g:663:4: rule__VideoGeneratorModel__MediasAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__VideoGeneratorModel__MediasAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVideoGeneratorModelAccess().getMediasAssignment_3()); 

            }

            // InternalVideoGen.g:666:2: ( ( rule__VideoGeneratorModel__MediasAssignment_3 )* )
            // InternalVideoGen.g:667:3: ( rule__VideoGeneratorModel__MediasAssignment_3 )*
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getMediasAssignment_3()); 
            // InternalVideoGen.g:668:3: ( rule__VideoGeneratorModel__MediasAssignment_3 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=24 && LA7_0<=26)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalVideoGen.g:668:4: rule__VideoGeneratorModel__MediasAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__VideoGeneratorModel__MediasAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getVideoGeneratorModelAccess().getMediasAssignment_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__3__Impl"


    // $ANTLR start "rule__VideoGeneratorModel__Group__4"
    // InternalVideoGen.g:677:1: rule__VideoGeneratorModel__Group__4 : rule__VideoGeneratorModel__Group__4__Impl ;
    public final void rule__VideoGeneratorModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:681:1: ( rule__VideoGeneratorModel__Group__4__Impl )
            // InternalVideoGen.g:682:2: rule__VideoGeneratorModel__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoGeneratorModel__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__4"


    // $ANTLR start "rule__VideoGeneratorModel__Group__4__Impl"
    // InternalVideoGen.g:688:1: rule__VideoGeneratorModel__Group__4__Impl : ( RULE_RIGHT_BRACKET ) ;
    public final void rule__VideoGeneratorModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:692:1: ( ( RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:693:1: ( RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:693:1: ( RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:694:2: RULE_RIGHT_BRACKET
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getRIGHT_BRACKETTerminalRuleCall_4()); 
            match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoGeneratorModelAccess().getRIGHT_BRACKETTerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__Group__4__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group__0"
    // InternalVideoGen.g:704:1: rule__VideoGenInformation__Group__0 : rule__VideoGenInformation__Group__0__Impl rule__VideoGenInformation__Group__1 ;
    public final void rule__VideoGenInformation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:708:1: ( rule__VideoGenInformation__Group__0__Impl rule__VideoGenInformation__Group__1 )
            // InternalVideoGen.g:709:2: rule__VideoGenInformation__Group__0__Impl rule__VideoGenInformation__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__VideoGenInformation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__0"


    // $ANTLR start "rule__VideoGenInformation__Group__0__Impl"
    // InternalVideoGen.g:716:1: rule__VideoGenInformation__Group__0__Impl : ( () ) ;
    public final void rule__VideoGenInformation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:720:1: ( ( () ) )
            // InternalVideoGen.g:721:1: ( () )
            {
            // InternalVideoGen.g:721:1: ( () )
            // InternalVideoGen.g:722:2: ()
            {
             before(grammarAccess.getVideoGenInformationAccess().getVideoGenInformationAction_0()); 
            // InternalVideoGen.g:723:2: ()
            // InternalVideoGen.g:723:3: 
            {
            }

             after(grammarAccess.getVideoGenInformationAccess().getVideoGenInformationAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__0__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group__1"
    // InternalVideoGen.g:731:1: rule__VideoGenInformation__Group__1 : rule__VideoGenInformation__Group__1__Impl rule__VideoGenInformation__Group__2 ;
    public final void rule__VideoGenInformation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:735:1: ( rule__VideoGenInformation__Group__1__Impl rule__VideoGenInformation__Group__2 )
            // InternalVideoGen.g:736:2: rule__VideoGenInformation__Group__1__Impl rule__VideoGenInformation__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__VideoGenInformation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__1"


    // $ANTLR start "rule__VideoGenInformation__Group__1__Impl"
    // InternalVideoGen.g:743:1: rule__VideoGenInformation__Group__1__Impl : ( ( rule__VideoGenInformation__Group_1__0 ) ) ;
    public final void rule__VideoGenInformation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:747:1: ( ( ( rule__VideoGenInformation__Group_1__0 ) ) )
            // InternalVideoGen.g:748:1: ( ( rule__VideoGenInformation__Group_1__0 ) )
            {
            // InternalVideoGen.g:748:1: ( ( rule__VideoGenInformation__Group_1__0 ) )
            // InternalVideoGen.g:749:2: ( rule__VideoGenInformation__Group_1__0 )
            {
             before(grammarAccess.getVideoGenInformationAccess().getGroup_1()); 
            // InternalVideoGen.g:750:2: ( rule__VideoGenInformation__Group_1__0 )
            // InternalVideoGen.g:750:3: rule__VideoGenInformation__Group_1__0
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_1__0();

            state._fsp--;


            }

             after(grammarAccess.getVideoGenInformationAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__1__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group__2"
    // InternalVideoGen.g:758:1: rule__VideoGenInformation__Group__2 : rule__VideoGenInformation__Group__2__Impl rule__VideoGenInformation__Group__3 ;
    public final void rule__VideoGenInformation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:762:1: ( rule__VideoGenInformation__Group__2__Impl rule__VideoGenInformation__Group__3 )
            // InternalVideoGen.g:763:2: rule__VideoGenInformation__Group__2__Impl rule__VideoGenInformation__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__VideoGenInformation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__2"


    // $ANTLR start "rule__VideoGenInformation__Group__2__Impl"
    // InternalVideoGen.g:770:1: rule__VideoGenInformation__Group__2__Impl : ( ( rule__VideoGenInformation__Group_2__0 )? ) ;
    public final void rule__VideoGenInformation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:774:1: ( ( ( rule__VideoGenInformation__Group_2__0 )? ) )
            // InternalVideoGen.g:775:1: ( ( rule__VideoGenInformation__Group_2__0 )? )
            {
            // InternalVideoGen.g:775:1: ( ( rule__VideoGenInformation__Group_2__0 )? )
            // InternalVideoGen.g:776:2: ( rule__VideoGenInformation__Group_2__0 )?
            {
             before(grammarAccess.getVideoGenInformationAccess().getGroup_2()); 
            // InternalVideoGen.g:777:2: ( rule__VideoGenInformation__Group_2__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==22) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalVideoGen.g:777:3: rule__VideoGenInformation__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoGenInformation__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoGenInformationAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__2__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group__3"
    // InternalVideoGen.g:785:1: rule__VideoGenInformation__Group__3 : rule__VideoGenInformation__Group__3__Impl ;
    public final void rule__VideoGenInformation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:789:1: ( rule__VideoGenInformation__Group__3__Impl )
            // InternalVideoGen.g:790:2: rule__VideoGenInformation__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__3"


    // $ANTLR start "rule__VideoGenInformation__Group__3__Impl"
    // InternalVideoGen.g:796:1: rule__VideoGenInformation__Group__3__Impl : ( ( rule__VideoGenInformation__Group_3__0 )? ) ;
    public final void rule__VideoGenInformation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:800:1: ( ( ( rule__VideoGenInformation__Group_3__0 )? ) )
            // InternalVideoGen.g:801:1: ( ( rule__VideoGenInformation__Group_3__0 )? )
            {
            // InternalVideoGen.g:801:1: ( ( rule__VideoGenInformation__Group_3__0 )? )
            // InternalVideoGen.g:802:2: ( rule__VideoGenInformation__Group_3__0 )?
            {
             before(grammarAccess.getVideoGenInformationAccess().getGroup_3()); 
            // InternalVideoGen.g:803:2: ( rule__VideoGenInformation__Group_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalVideoGen.g:803:3: rule__VideoGenInformation__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoGenInformation__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoGenInformationAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group__3__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_1__0"
    // InternalVideoGen.g:812:1: rule__VideoGenInformation__Group_1__0 : rule__VideoGenInformation__Group_1__0__Impl rule__VideoGenInformation__Group_1__1 ;
    public final void rule__VideoGenInformation__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:816:1: ( rule__VideoGenInformation__Group_1__0__Impl rule__VideoGenInformation__Group_1__1 )
            // InternalVideoGen.g:817:2: rule__VideoGenInformation__Group_1__0__Impl rule__VideoGenInformation__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__VideoGenInformation__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_1__0"


    // $ANTLR start "rule__VideoGenInformation__Group_1__0__Impl"
    // InternalVideoGen.g:824:1: rule__VideoGenInformation__Group_1__0__Impl : ( '@author' ) ;
    public final void rule__VideoGenInformation__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:828:1: ( ( '@author' ) )
            // InternalVideoGen.g:829:1: ( '@author' )
            {
            // InternalVideoGen.g:829:1: ( '@author' )
            // InternalVideoGen.g:830:2: '@author'
            {
             before(grammarAccess.getVideoGenInformationAccess().getAuthorKeyword_1_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getAuthorKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_1__0__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_1__1"
    // InternalVideoGen.g:839:1: rule__VideoGenInformation__Group_1__1 : rule__VideoGenInformation__Group_1__1__Impl ;
    public final void rule__VideoGenInformation__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:843:1: ( rule__VideoGenInformation__Group_1__1__Impl )
            // InternalVideoGen.g:844:2: rule__VideoGenInformation__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_1__1"


    // $ANTLR start "rule__VideoGenInformation__Group_1__1__Impl"
    // InternalVideoGen.g:850:1: rule__VideoGenInformation__Group_1__1__Impl : ( ( rule__VideoGenInformation__AuthorNameAssignment_1_1 ) ) ;
    public final void rule__VideoGenInformation__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:854:1: ( ( ( rule__VideoGenInformation__AuthorNameAssignment_1_1 ) ) )
            // InternalVideoGen.g:855:1: ( ( rule__VideoGenInformation__AuthorNameAssignment_1_1 ) )
            {
            // InternalVideoGen.g:855:1: ( ( rule__VideoGenInformation__AuthorNameAssignment_1_1 ) )
            // InternalVideoGen.g:856:2: ( rule__VideoGenInformation__AuthorNameAssignment_1_1 )
            {
             before(grammarAccess.getVideoGenInformationAccess().getAuthorNameAssignment_1_1()); 
            // InternalVideoGen.g:857:2: ( rule__VideoGenInformation__AuthorNameAssignment_1_1 )
            // InternalVideoGen.g:857:3: rule__VideoGenInformation__AuthorNameAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__AuthorNameAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoGenInformationAccess().getAuthorNameAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_1__1__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_2__0"
    // InternalVideoGen.g:866:1: rule__VideoGenInformation__Group_2__0 : rule__VideoGenInformation__Group_2__0__Impl rule__VideoGenInformation__Group_2__1 ;
    public final void rule__VideoGenInformation__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:870:1: ( rule__VideoGenInformation__Group_2__0__Impl rule__VideoGenInformation__Group_2__1 )
            // InternalVideoGen.g:871:2: rule__VideoGenInformation__Group_2__0__Impl rule__VideoGenInformation__Group_2__1
            {
            pushFollow(FOLLOW_10);
            rule__VideoGenInformation__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_2__0"


    // $ANTLR start "rule__VideoGenInformation__Group_2__0__Impl"
    // InternalVideoGen.g:878:1: rule__VideoGenInformation__Group_2__0__Impl : ( '@version' ) ;
    public final void rule__VideoGenInformation__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:882:1: ( ( '@version' ) )
            // InternalVideoGen.g:883:1: ( '@version' )
            {
            // InternalVideoGen.g:883:1: ( '@version' )
            // InternalVideoGen.g:884:2: '@version'
            {
             before(grammarAccess.getVideoGenInformationAccess().getVersionKeyword_2_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getVersionKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_2__0__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_2__1"
    // InternalVideoGen.g:893:1: rule__VideoGenInformation__Group_2__1 : rule__VideoGenInformation__Group_2__1__Impl ;
    public final void rule__VideoGenInformation__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:897:1: ( rule__VideoGenInformation__Group_2__1__Impl )
            // InternalVideoGen.g:898:2: rule__VideoGenInformation__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_2__1"


    // $ANTLR start "rule__VideoGenInformation__Group_2__1__Impl"
    // InternalVideoGen.g:904:1: rule__VideoGenInformation__Group_2__1__Impl : ( ( rule__VideoGenInformation__VersionAssignment_2_1 ) ) ;
    public final void rule__VideoGenInformation__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:908:1: ( ( ( rule__VideoGenInformation__VersionAssignment_2_1 ) ) )
            // InternalVideoGen.g:909:1: ( ( rule__VideoGenInformation__VersionAssignment_2_1 ) )
            {
            // InternalVideoGen.g:909:1: ( ( rule__VideoGenInformation__VersionAssignment_2_1 ) )
            // InternalVideoGen.g:910:2: ( rule__VideoGenInformation__VersionAssignment_2_1 )
            {
             before(grammarAccess.getVideoGenInformationAccess().getVersionAssignment_2_1()); 
            // InternalVideoGen.g:911:2: ( rule__VideoGenInformation__VersionAssignment_2_1 )
            // InternalVideoGen.g:911:3: rule__VideoGenInformation__VersionAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__VersionAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoGenInformationAccess().getVersionAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_2__1__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_3__0"
    // InternalVideoGen.g:920:1: rule__VideoGenInformation__Group_3__0 : rule__VideoGenInformation__Group_3__0__Impl rule__VideoGenInformation__Group_3__1 ;
    public final void rule__VideoGenInformation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:924:1: ( rule__VideoGenInformation__Group_3__0__Impl rule__VideoGenInformation__Group_3__1 )
            // InternalVideoGen.g:925:2: rule__VideoGenInformation__Group_3__0__Impl rule__VideoGenInformation__Group_3__1
            {
            pushFollow(FOLLOW_10);
            rule__VideoGenInformation__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_3__0"


    // $ANTLR start "rule__VideoGenInformation__Group_3__0__Impl"
    // InternalVideoGen.g:932:1: rule__VideoGenInformation__Group_3__0__Impl : ( '@creation' ) ;
    public final void rule__VideoGenInformation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:936:1: ( ( '@creation' ) )
            // InternalVideoGen.g:937:1: ( '@creation' )
            {
            // InternalVideoGen.g:937:1: ( '@creation' )
            // InternalVideoGen.g:938:2: '@creation'
            {
             before(grammarAccess.getVideoGenInformationAccess().getCreationKeyword_3_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getCreationKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_3__0__Impl"


    // $ANTLR start "rule__VideoGenInformation__Group_3__1"
    // InternalVideoGen.g:947:1: rule__VideoGenInformation__Group_3__1 : rule__VideoGenInformation__Group_3__1__Impl ;
    public final void rule__VideoGenInformation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:951:1: ( rule__VideoGenInformation__Group_3__1__Impl )
            // InternalVideoGen.g:952:2: rule__VideoGenInformation__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_3__1"


    // $ANTLR start "rule__VideoGenInformation__Group_3__1__Impl"
    // InternalVideoGen.g:958:1: rule__VideoGenInformation__Group_3__1__Impl : ( ( rule__VideoGenInformation__CreationDateAssignment_3_1 ) ) ;
    public final void rule__VideoGenInformation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:962:1: ( ( ( rule__VideoGenInformation__CreationDateAssignment_3_1 ) ) )
            // InternalVideoGen.g:963:1: ( ( rule__VideoGenInformation__CreationDateAssignment_3_1 ) )
            {
            // InternalVideoGen.g:963:1: ( ( rule__VideoGenInformation__CreationDateAssignment_3_1 ) )
            // InternalVideoGen.g:964:2: ( rule__VideoGenInformation__CreationDateAssignment_3_1 )
            {
             before(grammarAccess.getVideoGenInformationAccess().getCreationDateAssignment_3_1()); 
            // InternalVideoGen.g:965:2: ( rule__VideoGenInformation__CreationDateAssignment_3_1 )
            // InternalVideoGen.g:965:3: rule__VideoGenInformation__CreationDateAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoGenInformation__CreationDateAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoGenInformationAccess().getCreationDateAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__Group_3__1__Impl"


    // $ANTLR start "rule__MandatoryMedia__Group__0"
    // InternalVideoGen.g:974:1: rule__MandatoryMedia__Group__0 : rule__MandatoryMedia__Group__0__Impl rule__MandatoryMedia__Group__1 ;
    public final void rule__MandatoryMedia__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:978:1: ( rule__MandatoryMedia__Group__0__Impl rule__MandatoryMedia__Group__1 )
            // InternalVideoGen.g:979:2: rule__MandatoryMedia__Group__0__Impl rule__MandatoryMedia__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__MandatoryMedia__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MandatoryMedia__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryMedia__Group__0"


    // $ANTLR start "rule__MandatoryMedia__Group__0__Impl"
    // InternalVideoGen.g:986:1: rule__MandatoryMedia__Group__0__Impl : ( 'mandatory' ) ;
    public final void rule__MandatoryMedia__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:990:1: ( ( 'mandatory' ) )
            // InternalVideoGen.g:991:1: ( 'mandatory' )
            {
            // InternalVideoGen.g:991:1: ( 'mandatory' )
            // InternalVideoGen.g:992:2: 'mandatory'
            {
             before(grammarAccess.getMandatoryMediaAccess().getMandatoryKeyword_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getMandatoryMediaAccess().getMandatoryKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryMedia__Group__0__Impl"


    // $ANTLR start "rule__MandatoryMedia__Group__1"
    // InternalVideoGen.g:1001:1: rule__MandatoryMedia__Group__1 : rule__MandatoryMedia__Group__1__Impl ;
    public final void rule__MandatoryMedia__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1005:1: ( rule__MandatoryMedia__Group__1__Impl )
            // InternalVideoGen.g:1006:2: rule__MandatoryMedia__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MandatoryMedia__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryMedia__Group__1"


    // $ANTLR start "rule__MandatoryMedia__Group__1__Impl"
    // InternalVideoGen.g:1012:1: rule__MandatoryMedia__Group__1__Impl : ( ( rule__MandatoryMedia__DescriptionAssignment_1 ) ) ;
    public final void rule__MandatoryMedia__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1016:1: ( ( ( rule__MandatoryMedia__DescriptionAssignment_1 ) ) )
            // InternalVideoGen.g:1017:1: ( ( rule__MandatoryMedia__DescriptionAssignment_1 ) )
            {
            // InternalVideoGen.g:1017:1: ( ( rule__MandatoryMedia__DescriptionAssignment_1 ) )
            // InternalVideoGen.g:1018:2: ( rule__MandatoryMedia__DescriptionAssignment_1 )
            {
             before(grammarAccess.getMandatoryMediaAccess().getDescriptionAssignment_1()); 
            // InternalVideoGen.g:1019:2: ( rule__MandatoryMedia__DescriptionAssignment_1 )
            // InternalVideoGen.g:1019:3: rule__MandatoryMedia__DescriptionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__MandatoryMedia__DescriptionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMandatoryMediaAccess().getDescriptionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryMedia__Group__1__Impl"


    // $ANTLR start "rule__OptionalMedia__Group__0"
    // InternalVideoGen.g:1028:1: rule__OptionalMedia__Group__0 : rule__OptionalMedia__Group__0__Impl rule__OptionalMedia__Group__1 ;
    public final void rule__OptionalMedia__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1032:1: ( rule__OptionalMedia__Group__0__Impl rule__OptionalMedia__Group__1 )
            // InternalVideoGen.g:1033:2: rule__OptionalMedia__Group__0__Impl rule__OptionalMedia__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__OptionalMedia__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptionalMedia__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionalMedia__Group__0"


    // $ANTLR start "rule__OptionalMedia__Group__0__Impl"
    // InternalVideoGen.g:1040:1: rule__OptionalMedia__Group__0__Impl : ( 'optional' ) ;
    public final void rule__OptionalMedia__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1044:1: ( ( 'optional' ) )
            // InternalVideoGen.g:1045:1: ( 'optional' )
            {
            // InternalVideoGen.g:1045:1: ( 'optional' )
            // InternalVideoGen.g:1046:2: 'optional'
            {
             before(grammarAccess.getOptionalMediaAccess().getOptionalKeyword_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getOptionalMediaAccess().getOptionalKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionalMedia__Group__0__Impl"


    // $ANTLR start "rule__OptionalMedia__Group__1"
    // InternalVideoGen.g:1055:1: rule__OptionalMedia__Group__1 : rule__OptionalMedia__Group__1__Impl ;
    public final void rule__OptionalMedia__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1059:1: ( rule__OptionalMedia__Group__1__Impl )
            // InternalVideoGen.g:1060:2: rule__OptionalMedia__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OptionalMedia__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionalMedia__Group__1"


    // $ANTLR start "rule__OptionalMedia__Group__1__Impl"
    // InternalVideoGen.g:1066:1: rule__OptionalMedia__Group__1__Impl : ( ( rule__OptionalMedia__DescriptionAssignment_1 ) ) ;
    public final void rule__OptionalMedia__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1070:1: ( ( ( rule__OptionalMedia__DescriptionAssignment_1 ) ) )
            // InternalVideoGen.g:1071:1: ( ( rule__OptionalMedia__DescriptionAssignment_1 ) )
            {
            // InternalVideoGen.g:1071:1: ( ( rule__OptionalMedia__DescriptionAssignment_1 ) )
            // InternalVideoGen.g:1072:2: ( rule__OptionalMedia__DescriptionAssignment_1 )
            {
             before(grammarAccess.getOptionalMediaAccess().getDescriptionAssignment_1()); 
            // InternalVideoGen.g:1073:2: ( rule__OptionalMedia__DescriptionAssignment_1 )
            // InternalVideoGen.g:1073:3: rule__OptionalMedia__DescriptionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__OptionalMedia__DescriptionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOptionalMediaAccess().getDescriptionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionalMedia__Group__1__Impl"


    // $ANTLR start "rule__AlternativesMedia__Group__0"
    // InternalVideoGen.g:1082:1: rule__AlternativesMedia__Group__0 : rule__AlternativesMedia__Group__0__Impl rule__AlternativesMedia__Group__1 ;
    public final void rule__AlternativesMedia__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1086:1: ( rule__AlternativesMedia__Group__0__Impl rule__AlternativesMedia__Group__1 )
            // InternalVideoGen.g:1087:2: rule__AlternativesMedia__Group__0__Impl rule__AlternativesMedia__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__AlternativesMedia__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__0"


    // $ANTLR start "rule__AlternativesMedia__Group__0__Impl"
    // InternalVideoGen.g:1094:1: rule__AlternativesMedia__Group__0__Impl : ( 'alternatives' ) ;
    public final void rule__AlternativesMedia__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1098:1: ( ( 'alternatives' ) )
            // InternalVideoGen.g:1099:1: ( 'alternatives' )
            {
            // InternalVideoGen.g:1099:1: ( 'alternatives' )
            // InternalVideoGen.g:1100:2: 'alternatives'
            {
             before(grammarAccess.getAlternativesMediaAccess().getAlternativesKeyword_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getAlternativesMediaAccess().getAlternativesKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__0__Impl"


    // $ANTLR start "rule__AlternativesMedia__Group__1"
    // InternalVideoGen.g:1109:1: rule__AlternativesMedia__Group__1 : rule__AlternativesMedia__Group__1__Impl rule__AlternativesMedia__Group__2 ;
    public final void rule__AlternativesMedia__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1113:1: ( rule__AlternativesMedia__Group__1__Impl rule__AlternativesMedia__Group__2 )
            // InternalVideoGen.g:1114:2: rule__AlternativesMedia__Group__1__Impl rule__AlternativesMedia__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__AlternativesMedia__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__1"


    // $ANTLR start "rule__AlternativesMedia__Group__1__Impl"
    // InternalVideoGen.g:1121:1: rule__AlternativesMedia__Group__1__Impl : ( ( rule__AlternativesMedia__IdAssignment_1 )? ) ;
    public final void rule__AlternativesMedia__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1125:1: ( ( ( rule__AlternativesMedia__IdAssignment_1 )? ) )
            // InternalVideoGen.g:1126:1: ( ( rule__AlternativesMedia__IdAssignment_1 )? )
            {
            // InternalVideoGen.g:1126:1: ( ( rule__AlternativesMedia__IdAssignment_1 )? )
            // InternalVideoGen.g:1127:2: ( rule__AlternativesMedia__IdAssignment_1 )?
            {
             before(grammarAccess.getAlternativesMediaAccess().getIdAssignment_1()); 
            // InternalVideoGen.g:1128:2: ( rule__AlternativesMedia__IdAssignment_1 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalVideoGen.g:1128:3: rule__AlternativesMedia__IdAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__AlternativesMedia__IdAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAlternativesMediaAccess().getIdAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__1__Impl"


    // $ANTLR start "rule__AlternativesMedia__Group__2"
    // InternalVideoGen.g:1136:1: rule__AlternativesMedia__Group__2 : rule__AlternativesMedia__Group__2__Impl rule__AlternativesMedia__Group__3 ;
    public final void rule__AlternativesMedia__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1140:1: ( rule__AlternativesMedia__Group__2__Impl rule__AlternativesMedia__Group__3 )
            // InternalVideoGen.g:1141:2: rule__AlternativesMedia__Group__2__Impl rule__AlternativesMedia__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__AlternativesMedia__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__2"


    // $ANTLR start "rule__AlternativesMedia__Group__2__Impl"
    // InternalVideoGen.g:1148:1: rule__AlternativesMedia__Group__2__Impl : ( RULE_LEFT_BRACKET ) ;
    public final void rule__AlternativesMedia__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1152:1: ( ( RULE_LEFT_BRACKET ) )
            // InternalVideoGen.g:1153:1: ( RULE_LEFT_BRACKET )
            {
            // InternalVideoGen.g:1153:1: ( RULE_LEFT_BRACKET )
            // InternalVideoGen.g:1154:2: RULE_LEFT_BRACKET
            {
             before(grammarAccess.getAlternativesMediaAccess().getLEFT_BRACKETTerminalRuleCall_2()); 
            match(input,RULE_LEFT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getAlternativesMediaAccess().getLEFT_BRACKETTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__2__Impl"


    // $ANTLR start "rule__AlternativesMedia__Group__3"
    // InternalVideoGen.g:1163:1: rule__AlternativesMedia__Group__3 : rule__AlternativesMedia__Group__3__Impl rule__AlternativesMedia__Group__4 ;
    public final void rule__AlternativesMedia__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1167:1: ( rule__AlternativesMedia__Group__3__Impl rule__AlternativesMedia__Group__4 )
            // InternalVideoGen.g:1168:2: rule__AlternativesMedia__Group__3__Impl rule__AlternativesMedia__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__AlternativesMedia__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__3"


    // $ANTLR start "rule__AlternativesMedia__Group__3__Impl"
    // InternalVideoGen.g:1175:1: rule__AlternativesMedia__Group__3__Impl : ( ( ( rule__AlternativesMedia__MediasAssignment_3 ) ) ( ( rule__AlternativesMedia__MediasAssignment_3 )* ) ) ;
    public final void rule__AlternativesMedia__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1179:1: ( ( ( ( rule__AlternativesMedia__MediasAssignment_3 ) ) ( ( rule__AlternativesMedia__MediasAssignment_3 )* ) ) )
            // InternalVideoGen.g:1180:1: ( ( ( rule__AlternativesMedia__MediasAssignment_3 ) ) ( ( rule__AlternativesMedia__MediasAssignment_3 )* ) )
            {
            // InternalVideoGen.g:1180:1: ( ( ( rule__AlternativesMedia__MediasAssignment_3 ) ) ( ( rule__AlternativesMedia__MediasAssignment_3 )* ) )
            // InternalVideoGen.g:1181:2: ( ( rule__AlternativesMedia__MediasAssignment_3 ) ) ( ( rule__AlternativesMedia__MediasAssignment_3 )* )
            {
            // InternalVideoGen.g:1181:2: ( ( rule__AlternativesMedia__MediasAssignment_3 ) )
            // InternalVideoGen.g:1182:3: ( rule__AlternativesMedia__MediasAssignment_3 )
            {
             before(grammarAccess.getAlternativesMediaAccess().getMediasAssignment_3()); 
            // InternalVideoGen.g:1183:3: ( rule__AlternativesMedia__MediasAssignment_3 )
            // InternalVideoGen.g:1183:4: rule__AlternativesMedia__MediasAssignment_3
            {
            pushFollow(FOLLOW_13);
            rule__AlternativesMedia__MediasAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getAlternativesMediaAccess().getMediasAssignment_3()); 

            }

            // InternalVideoGen.g:1186:2: ( ( rule__AlternativesMedia__MediasAssignment_3 )* )
            // InternalVideoGen.g:1187:3: ( rule__AlternativesMedia__MediasAssignment_3 )*
            {
             before(grammarAccess.getAlternativesMediaAccess().getMediasAssignment_3()); 
            // InternalVideoGen.g:1188:3: ( rule__AlternativesMedia__MediasAssignment_3 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==27||LA11_0==30) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalVideoGen.g:1188:4: rule__AlternativesMedia__MediasAssignment_3
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__AlternativesMedia__MediasAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getAlternativesMediaAccess().getMediasAssignment_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__3__Impl"


    // $ANTLR start "rule__AlternativesMedia__Group__4"
    // InternalVideoGen.g:1197:1: rule__AlternativesMedia__Group__4 : rule__AlternativesMedia__Group__4__Impl ;
    public final void rule__AlternativesMedia__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1201:1: ( rule__AlternativesMedia__Group__4__Impl )
            // InternalVideoGen.g:1202:2: rule__AlternativesMedia__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AlternativesMedia__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__4"


    // $ANTLR start "rule__AlternativesMedia__Group__4__Impl"
    // InternalVideoGen.g:1208:1: rule__AlternativesMedia__Group__4__Impl : ( RULE_RIGHT_BRACKET ) ;
    public final void rule__AlternativesMedia__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1212:1: ( ( RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:1213:1: ( RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:1213:1: ( RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:1214:2: RULE_RIGHT_BRACKET
            {
             before(grammarAccess.getAlternativesMediaAccess().getRIGHT_BRACKETTerminalRuleCall_4()); 
            match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getAlternativesMediaAccess().getRIGHT_BRACKETTerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__Group__4__Impl"


    // $ANTLR start "rule__ImageDescription__Group__0"
    // InternalVideoGen.g:1224:1: rule__ImageDescription__Group__0 : rule__ImageDescription__Group__0__Impl rule__ImageDescription__Group__1 ;
    public final void rule__ImageDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1228:1: ( rule__ImageDescription__Group__0__Impl rule__ImageDescription__Group__1 )
            // InternalVideoGen.g:1229:2: rule__ImageDescription__Group__0__Impl rule__ImageDescription__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__ImageDescription__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__0"


    // $ANTLR start "rule__ImageDescription__Group__0__Impl"
    // InternalVideoGen.g:1236:1: rule__ImageDescription__Group__0__Impl : ( 'image' ) ;
    public final void rule__ImageDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1240:1: ( ( 'image' ) )
            // InternalVideoGen.g:1241:1: ( 'image' )
            {
            // InternalVideoGen.g:1241:1: ( 'image' )
            // InternalVideoGen.g:1242:2: 'image'
            {
             before(grammarAccess.getImageDescriptionAccess().getImageKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getImageKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__0__Impl"


    // $ANTLR start "rule__ImageDescription__Group__1"
    // InternalVideoGen.g:1251:1: rule__ImageDescription__Group__1 : rule__ImageDescription__Group__1__Impl rule__ImageDescription__Group__2 ;
    public final void rule__ImageDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1255:1: ( rule__ImageDescription__Group__1__Impl rule__ImageDescription__Group__2 )
            // InternalVideoGen.g:1256:2: rule__ImageDescription__Group__1__Impl rule__ImageDescription__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ImageDescription__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__1"


    // $ANTLR start "rule__ImageDescription__Group__1__Impl"
    // InternalVideoGen.g:1263:1: rule__ImageDescription__Group__1__Impl : ( ( rule__ImageDescription__ImageidAssignment_1 )? ) ;
    public final void rule__ImageDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1267:1: ( ( ( rule__ImageDescription__ImageidAssignment_1 )? ) )
            // InternalVideoGen.g:1268:1: ( ( rule__ImageDescription__ImageidAssignment_1 )? )
            {
            // InternalVideoGen.g:1268:1: ( ( rule__ImageDescription__ImageidAssignment_1 )? )
            // InternalVideoGen.g:1269:2: ( rule__ImageDescription__ImageidAssignment_1 )?
            {
             before(grammarAccess.getImageDescriptionAccess().getImageidAssignment_1()); 
            // InternalVideoGen.g:1270:2: ( rule__ImageDescription__ImageidAssignment_1 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalVideoGen.g:1270:3: rule__ImageDescription__ImageidAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImageDescription__ImageidAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImageDescriptionAccess().getImageidAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__1__Impl"


    // $ANTLR start "rule__ImageDescription__Group__2"
    // InternalVideoGen.g:1278:1: rule__ImageDescription__Group__2 : rule__ImageDescription__Group__2__Impl rule__ImageDescription__Group__3 ;
    public final void rule__ImageDescription__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1282:1: ( rule__ImageDescription__Group__2__Impl rule__ImageDescription__Group__3 )
            // InternalVideoGen.g:1283:2: rule__ImageDescription__Group__2__Impl rule__ImageDescription__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__ImageDescription__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__2"


    // $ANTLR start "rule__ImageDescription__Group__2__Impl"
    // InternalVideoGen.g:1290:1: rule__ImageDescription__Group__2__Impl : ( ( rule__ImageDescription__LocationAssignment_2 ) ) ;
    public final void rule__ImageDescription__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1294:1: ( ( ( rule__ImageDescription__LocationAssignment_2 ) ) )
            // InternalVideoGen.g:1295:1: ( ( rule__ImageDescription__LocationAssignment_2 ) )
            {
            // InternalVideoGen.g:1295:1: ( ( rule__ImageDescription__LocationAssignment_2 ) )
            // InternalVideoGen.g:1296:2: ( rule__ImageDescription__LocationAssignment_2 )
            {
             before(grammarAccess.getImageDescriptionAccess().getLocationAssignment_2()); 
            // InternalVideoGen.g:1297:2: ( rule__ImageDescription__LocationAssignment_2 )
            // InternalVideoGen.g:1297:3: rule__ImageDescription__LocationAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__LocationAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getLocationAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__2__Impl"


    // $ANTLR start "rule__ImageDescription__Group__3"
    // InternalVideoGen.g:1305:1: rule__ImageDescription__Group__3 : rule__ImageDescription__Group__3__Impl ;
    public final void rule__ImageDescription__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1309:1: ( rule__ImageDescription__Group__3__Impl )
            // InternalVideoGen.g:1310:2: rule__ImageDescription__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__3"


    // $ANTLR start "rule__ImageDescription__Group__3__Impl"
    // InternalVideoGen.g:1316:1: rule__ImageDescription__Group__3__Impl : ( ( rule__ImageDescription__Group_3__0 )? ) ;
    public final void rule__ImageDescription__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1320:1: ( ( ( rule__ImageDescription__Group_3__0 )? ) )
            // InternalVideoGen.g:1321:1: ( ( rule__ImageDescription__Group_3__0 )? )
            {
            // InternalVideoGen.g:1321:1: ( ( rule__ImageDescription__Group_3__0 )? )
            // InternalVideoGen.g:1322:2: ( rule__ImageDescription__Group_3__0 )?
            {
             before(grammarAccess.getImageDescriptionAccess().getGroup_3()); 
            // InternalVideoGen.g:1323:2: ( rule__ImageDescription__Group_3__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_LEFT_BRACKET) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalVideoGen.g:1323:3: rule__ImageDescription__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImageDescription__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImageDescriptionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group__3__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3__0"
    // InternalVideoGen.g:1332:1: rule__ImageDescription__Group_3__0 : rule__ImageDescription__Group_3__0__Impl rule__ImageDescription__Group_3__1 ;
    public final void rule__ImageDescription__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1336:1: ( rule__ImageDescription__Group_3__0__Impl rule__ImageDescription__Group_3__1 )
            // InternalVideoGen.g:1337:2: rule__ImageDescription__Group_3__0__Impl rule__ImageDescription__Group_3__1
            {
            pushFollow(FOLLOW_15);
            rule__ImageDescription__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__0"


    // $ANTLR start "rule__ImageDescription__Group_3__0__Impl"
    // InternalVideoGen.g:1344:1: rule__ImageDescription__Group_3__0__Impl : ( RULE_LEFT_BRACKET ) ;
    public final void rule__ImageDescription__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1348:1: ( ( RULE_LEFT_BRACKET ) )
            // InternalVideoGen.g:1349:1: ( RULE_LEFT_BRACKET )
            {
            // InternalVideoGen.g:1349:1: ( RULE_LEFT_BRACKET )
            // InternalVideoGen.g:1350:2: RULE_LEFT_BRACKET
            {
             before(grammarAccess.getImageDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0()); 
            match(input,RULE_LEFT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__0__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3__1"
    // InternalVideoGen.g:1359:1: rule__ImageDescription__Group_3__1 : rule__ImageDescription__Group_3__1__Impl rule__ImageDescription__Group_3__2 ;
    public final void rule__ImageDescription__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1363:1: ( rule__ImageDescription__Group_3__1__Impl rule__ImageDescription__Group_3__2 )
            // InternalVideoGen.g:1364:2: rule__ImageDescription__Group_3__1__Impl rule__ImageDescription__Group_3__2
            {
            pushFollow(FOLLOW_16);
            rule__ImageDescription__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__1"


    // $ANTLR start "rule__ImageDescription__Group_3__1__Impl"
    // InternalVideoGen.g:1371:1: rule__ImageDescription__Group_3__1__Impl : ( ( rule__ImageDescription__Group_3_1__0 ) ) ;
    public final void rule__ImageDescription__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1375:1: ( ( ( rule__ImageDescription__Group_3_1__0 ) ) )
            // InternalVideoGen.g:1376:1: ( ( rule__ImageDescription__Group_3_1__0 ) )
            {
            // InternalVideoGen.g:1376:1: ( ( rule__ImageDescription__Group_3_1__0 ) )
            // InternalVideoGen.g:1377:2: ( rule__ImageDescription__Group_3_1__0 )
            {
             before(grammarAccess.getImageDescriptionAccess().getGroup_3_1()); 
            // InternalVideoGen.g:1378:2: ( rule__ImageDescription__Group_3_1__0 )
            // InternalVideoGen.g:1378:3: rule__ImageDescription__Group_3_1__0
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_1__0();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getGroup_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__1__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3__2"
    // InternalVideoGen.g:1386:1: rule__ImageDescription__Group_3__2 : rule__ImageDescription__Group_3__2__Impl rule__ImageDescription__Group_3__3 ;
    public final void rule__ImageDescription__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1390:1: ( rule__ImageDescription__Group_3__2__Impl rule__ImageDescription__Group_3__3 )
            // InternalVideoGen.g:1391:2: rule__ImageDescription__Group_3__2__Impl rule__ImageDescription__Group_3__3
            {
            pushFollow(FOLLOW_6);
            rule__ImageDescription__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__2"


    // $ANTLR start "rule__ImageDescription__Group_3__2__Impl"
    // InternalVideoGen.g:1398:1: rule__ImageDescription__Group_3__2__Impl : ( ( rule__ImageDescription__Group_3_2__0 ) ) ;
    public final void rule__ImageDescription__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1402:1: ( ( ( rule__ImageDescription__Group_3_2__0 ) ) )
            // InternalVideoGen.g:1403:1: ( ( rule__ImageDescription__Group_3_2__0 ) )
            {
            // InternalVideoGen.g:1403:1: ( ( rule__ImageDescription__Group_3_2__0 ) )
            // InternalVideoGen.g:1404:2: ( rule__ImageDescription__Group_3_2__0 )
            {
             before(grammarAccess.getImageDescriptionAccess().getGroup_3_2()); 
            // InternalVideoGen.g:1405:2: ( rule__ImageDescription__Group_3_2__0 )
            // InternalVideoGen.g:1405:3: rule__ImageDescription__Group_3_2__0
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_2__0();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getGroup_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__2__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3__3"
    // InternalVideoGen.g:1413:1: rule__ImageDescription__Group_3__3 : rule__ImageDescription__Group_3__3__Impl ;
    public final void rule__ImageDescription__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1417:1: ( rule__ImageDescription__Group_3__3__Impl )
            // InternalVideoGen.g:1418:2: rule__ImageDescription__Group_3__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__3"


    // $ANTLR start "rule__ImageDescription__Group_3__3__Impl"
    // InternalVideoGen.g:1424:1: rule__ImageDescription__Group_3__3__Impl : ( RULE_RIGHT_BRACKET ) ;
    public final void rule__ImageDescription__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1428:1: ( ( RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:1429:1: ( RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:1429:1: ( RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:1430:2: RULE_RIGHT_BRACKET
            {
             before(grammarAccess.getImageDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_3()); 
            match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3__3__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3_1__0"
    // InternalVideoGen.g:1440:1: rule__ImageDescription__Group_3_1__0 : rule__ImageDescription__Group_3_1__0__Impl rule__ImageDescription__Group_3_1__1 ;
    public final void rule__ImageDescription__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1444:1: ( rule__ImageDescription__Group_3_1__0__Impl rule__ImageDescription__Group_3_1__1 )
            // InternalVideoGen.g:1445:2: rule__ImageDescription__Group_3_1__0__Impl rule__ImageDescription__Group_3_1__1
            {
            pushFollow(FOLLOW_10);
            rule__ImageDescription__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_1__0"


    // $ANTLR start "rule__ImageDescription__Group_3_1__0__Impl"
    // InternalVideoGen.g:1452:1: rule__ImageDescription__Group_3_1__0__Impl : ( 'toptext' ) ;
    public final void rule__ImageDescription__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1456:1: ( ( 'toptext' ) )
            // InternalVideoGen.g:1457:1: ( 'toptext' )
            {
            // InternalVideoGen.g:1457:1: ( 'toptext' )
            // InternalVideoGen.g:1458:2: 'toptext'
            {
             before(grammarAccess.getImageDescriptionAccess().getToptextKeyword_3_1_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getToptextKeyword_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_1__0__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3_1__1"
    // InternalVideoGen.g:1467:1: rule__ImageDescription__Group_3_1__1 : rule__ImageDescription__Group_3_1__1__Impl ;
    public final void rule__ImageDescription__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1471:1: ( rule__ImageDescription__Group_3_1__1__Impl )
            // InternalVideoGen.g:1472:2: rule__ImageDescription__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_1__1"


    // $ANTLR start "rule__ImageDescription__Group_3_1__1__Impl"
    // InternalVideoGen.g:1478:1: rule__ImageDescription__Group_3_1__1__Impl : ( ( rule__ImageDescription__TopAssignment_3_1_1 ) ) ;
    public final void rule__ImageDescription__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1482:1: ( ( ( rule__ImageDescription__TopAssignment_3_1_1 ) ) )
            // InternalVideoGen.g:1483:1: ( ( rule__ImageDescription__TopAssignment_3_1_1 ) )
            {
            // InternalVideoGen.g:1483:1: ( ( rule__ImageDescription__TopAssignment_3_1_1 ) )
            // InternalVideoGen.g:1484:2: ( rule__ImageDescription__TopAssignment_3_1_1 )
            {
             before(grammarAccess.getImageDescriptionAccess().getTopAssignment_3_1_1()); 
            // InternalVideoGen.g:1485:2: ( rule__ImageDescription__TopAssignment_3_1_1 )
            // InternalVideoGen.g:1485:3: rule__ImageDescription__TopAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__TopAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getTopAssignment_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_1__1__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3_2__0"
    // InternalVideoGen.g:1494:1: rule__ImageDescription__Group_3_2__0 : rule__ImageDescription__Group_3_2__0__Impl rule__ImageDescription__Group_3_2__1 ;
    public final void rule__ImageDescription__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1498:1: ( rule__ImageDescription__Group_3_2__0__Impl rule__ImageDescription__Group_3_2__1 )
            // InternalVideoGen.g:1499:2: rule__ImageDescription__Group_3_2__0__Impl rule__ImageDescription__Group_3_2__1
            {
            pushFollow(FOLLOW_10);
            rule__ImageDescription__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_2__0"


    // $ANTLR start "rule__ImageDescription__Group_3_2__0__Impl"
    // InternalVideoGen.g:1506:1: rule__ImageDescription__Group_3_2__0__Impl : ( 'bottomtext' ) ;
    public final void rule__ImageDescription__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1510:1: ( ( 'bottomtext' ) )
            // InternalVideoGen.g:1511:1: ( 'bottomtext' )
            {
            // InternalVideoGen.g:1511:1: ( 'bottomtext' )
            // InternalVideoGen.g:1512:2: 'bottomtext'
            {
             before(grammarAccess.getImageDescriptionAccess().getBottomtextKeyword_3_2_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getBottomtextKeyword_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_2__0__Impl"


    // $ANTLR start "rule__ImageDescription__Group_3_2__1"
    // InternalVideoGen.g:1521:1: rule__ImageDescription__Group_3_2__1 : rule__ImageDescription__Group_3_2__1__Impl ;
    public final void rule__ImageDescription__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1525:1: ( rule__ImageDescription__Group_3_2__1__Impl )
            // InternalVideoGen.g:1526:2: rule__ImageDescription__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__Group_3_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_2__1"


    // $ANTLR start "rule__ImageDescription__Group_3_2__1__Impl"
    // InternalVideoGen.g:1532:1: rule__ImageDescription__Group_3_2__1__Impl : ( ( rule__ImageDescription__BottomAssignment_3_2_1 ) ) ;
    public final void rule__ImageDescription__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1536:1: ( ( ( rule__ImageDescription__BottomAssignment_3_2_1 ) ) )
            // InternalVideoGen.g:1537:1: ( ( rule__ImageDescription__BottomAssignment_3_2_1 ) )
            {
            // InternalVideoGen.g:1537:1: ( ( rule__ImageDescription__BottomAssignment_3_2_1 ) )
            // InternalVideoGen.g:1538:2: ( rule__ImageDescription__BottomAssignment_3_2_1 )
            {
             before(grammarAccess.getImageDescriptionAccess().getBottomAssignment_3_2_1()); 
            // InternalVideoGen.g:1539:2: ( rule__ImageDescription__BottomAssignment_3_2_1 )
            // InternalVideoGen.g:1539:3: rule__ImageDescription__BottomAssignment_3_2_1
            {
            pushFollow(FOLLOW_2);
            rule__ImageDescription__BottomAssignment_3_2_1();

            state._fsp--;


            }

             after(grammarAccess.getImageDescriptionAccess().getBottomAssignment_3_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__Group_3_2__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group__0"
    // InternalVideoGen.g:1548:1: rule__VideoDescription__Group__0 : rule__VideoDescription__Group__0__Impl rule__VideoDescription__Group__1 ;
    public final void rule__VideoDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1552:1: ( rule__VideoDescription__Group__0__Impl rule__VideoDescription__Group__1 )
            // InternalVideoGen.g:1553:2: rule__VideoDescription__Group__0__Impl rule__VideoDescription__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__VideoDescription__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__0"


    // $ANTLR start "rule__VideoDescription__Group__0__Impl"
    // InternalVideoGen.g:1560:1: rule__VideoDescription__Group__0__Impl : ( 'videoseq' ) ;
    public final void rule__VideoDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1564:1: ( ( 'videoseq' ) )
            // InternalVideoGen.g:1565:1: ( 'videoseq' )
            {
            // InternalVideoGen.g:1565:1: ( 'videoseq' )
            // InternalVideoGen.g:1566:2: 'videoseq'
            {
             before(grammarAccess.getVideoDescriptionAccess().getVideoseqKeyword_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getVideoseqKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group__1"
    // InternalVideoGen.g:1575:1: rule__VideoDescription__Group__1 : rule__VideoDescription__Group__1__Impl rule__VideoDescription__Group__2 ;
    public final void rule__VideoDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1579:1: ( rule__VideoDescription__Group__1__Impl rule__VideoDescription__Group__2 )
            // InternalVideoGen.g:1580:2: rule__VideoDescription__Group__1__Impl rule__VideoDescription__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__VideoDescription__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__1"


    // $ANTLR start "rule__VideoDescription__Group__1__Impl"
    // InternalVideoGen.g:1587:1: rule__VideoDescription__Group__1__Impl : ( ( rule__VideoDescription__VideoidAssignment_1 )? ) ;
    public final void rule__VideoDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1591:1: ( ( ( rule__VideoDescription__VideoidAssignment_1 )? ) )
            // InternalVideoGen.g:1592:1: ( ( rule__VideoDescription__VideoidAssignment_1 )? )
            {
            // InternalVideoGen.g:1592:1: ( ( rule__VideoDescription__VideoidAssignment_1 )? )
            // InternalVideoGen.g:1593:2: ( rule__VideoDescription__VideoidAssignment_1 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getVideoidAssignment_1()); 
            // InternalVideoGen.g:1594:2: ( rule__VideoDescription__VideoidAssignment_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalVideoGen.g:1594:3: rule__VideoDescription__VideoidAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__VideoidAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getVideoidAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group__2"
    // InternalVideoGen.g:1602:1: rule__VideoDescription__Group__2 : rule__VideoDescription__Group__2__Impl rule__VideoDescription__Group__3 ;
    public final void rule__VideoDescription__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1606:1: ( rule__VideoDescription__Group__2__Impl rule__VideoDescription__Group__3 )
            // InternalVideoGen.g:1607:2: rule__VideoDescription__Group__2__Impl rule__VideoDescription__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__VideoDescription__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__2"


    // $ANTLR start "rule__VideoDescription__Group__2__Impl"
    // InternalVideoGen.g:1614:1: rule__VideoDescription__Group__2__Impl : ( ( rule__VideoDescription__LocationAssignment_2 ) ) ;
    public final void rule__VideoDescription__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1618:1: ( ( ( rule__VideoDescription__LocationAssignment_2 ) ) )
            // InternalVideoGen.g:1619:1: ( ( rule__VideoDescription__LocationAssignment_2 ) )
            {
            // InternalVideoGen.g:1619:1: ( ( rule__VideoDescription__LocationAssignment_2 ) )
            // InternalVideoGen.g:1620:2: ( rule__VideoDescription__LocationAssignment_2 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getLocationAssignment_2()); 
            // InternalVideoGen.g:1621:2: ( rule__VideoDescription__LocationAssignment_2 )
            // InternalVideoGen.g:1621:3: rule__VideoDescription__LocationAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__LocationAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getLocationAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__2__Impl"


    // $ANTLR start "rule__VideoDescription__Group__3"
    // InternalVideoGen.g:1629:1: rule__VideoDescription__Group__3 : rule__VideoDescription__Group__3__Impl ;
    public final void rule__VideoDescription__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1633:1: ( rule__VideoDescription__Group__3__Impl )
            // InternalVideoGen.g:1634:2: rule__VideoDescription__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__3"


    // $ANTLR start "rule__VideoDescription__Group__3__Impl"
    // InternalVideoGen.g:1640:1: rule__VideoDescription__Group__3__Impl : ( ( rule__VideoDescription__Group_3__0 )? ) ;
    public final void rule__VideoDescription__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1644:1: ( ( ( rule__VideoDescription__Group_3__0 )? ) )
            // InternalVideoGen.g:1645:1: ( ( rule__VideoDescription__Group_3__0 )? )
            {
            // InternalVideoGen.g:1645:1: ( ( rule__VideoDescription__Group_3__0 )? )
            // InternalVideoGen.g:1646:2: ( rule__VideoDescription__Group_3__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3()); 
            // InternalVideoGen.g:1647:2: ( rule__VideoDescription__Group_3__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_LEFT_BRACKET) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalVideoGen.g:1647:3: rule__VideoDescription__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group__3__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__0"
    // InternalVideoGen.g:1656:1: rule__VideoDescription__Group_3__0 : rule__VideoDescription__Group_3__0__Impl rule__VideoDescription__Group_3__1 ;
    public final void rule__VideoDescription__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1660:1: ( rule__VideoDescription__Group_3__0__Impl rule__VideoDescription__Group_3__1 )
            // InternalVideoGen.g:1661:2: rule__VideoDescription__Group_3__0__Impl rule__VideoDescription__Group_3__1
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__0"


    // $ANTLR start "rule__VideoDescription__Group_3__0__Impl"
    // InternalVideoGen.g:1668:1: rule__VideoDescription__Group_3__0__Impl : ( RULE_LEFT_BRACKET ) ;
    public final void rule__VideoDescription__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1672:1: ( ( RULE_LEFT_BRACKET ) )
            // InternalVideoGen.g:1673:1: ( RULE_LEFT_BRACKET )
            {
            // InternalVideoGen.g:1673:1: ( RULE_LEFT_BRACKET )
            // InternalVideoGen.g:1674:2: RULE_LEFT_BRACKET
            {
             before(grammarAccess.getVideoDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0()); 
            match(input,RULE_LEFT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__1"
    // InternalVideoGen.g:1683:1: rule__VideoDescription__Group_3__1 : rule__VideoDescription__Group_3__1__Impl rule__VideoDescription__Group_3__2 ;
    public final void rule__VideoDescription__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1687:1: ( rule__VideoDescription__Group_3__1__Impl rule__VideoDescription__Group_3__2 )
            // InternalVideoGen.g:1688:2: rule__VideoDescription__Group_3__1__Impl rule__VideoDescription__Group_3__2
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__1"


    // $ANTLR start "rule__VideoDescription__Group_3__1__Impl"
    // InternalVideoGen.g:1695:1: rule__VideoDescription__Group_3__1__Impl : ( ( rule__VideoDescription__Group_3_1__0 )? ) ;
    public final void rule__VideoDescription__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1699:1: ( ( ( rule__VideoDescription__Group_3_1__0 )? ) )
            // InternalVideoGen.g:1700:1: ( ( rule__VideoDescription__Group_3_1__0 )? )
            {
            // InternalVideoGen.g:1700:1: ( ( rule__VideoDescription__Group_3_1__0 )? )
            // InternalVideoGen.g:1701:2: ( rule__VideoDescription__Group_3_1__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3_1()); 
            // InternalVideoGen.g:1702:2: ( rule__VideoDescription__Group_3_1__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==31) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalVideoGen.g:1702:3: rule__VideoDescription__Group_3_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__2"
    // InternalVideoGen.g:1710:1: rule__VideoDescription__Group_3__2 : rule__VideoDescription__Group_3__2__Impl rule__VideoDescription__Group_3__3 ;
    public final void rule__VideoDescription__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1714:1: ( rule__VideoDescription__Group_3__2__Impl rule__VideoDescription__Group_3__3 )
            // InternalVideoGen.g:1715:2: rule__VideoDescription__Group_3__2__Impl rule__VideoDescription__Group_3__3
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__2"


    // $ANTLR start "rule__VideoDescription__Group_3__2__Impl"
    // InternalVideoGen.g:1722:1: rule__VideoDescription__Group_3__2__Impl : ( ( rule__VideoDescription__Group_3_2__0 )? ) ;
    public final void rule__VideoDescription__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1726:1: ( ( ( rule__VideoDescription__Group_3_2__0 )? ) )
            // InternalVideoGen.g:1727:1: ( ( rule__VideoDescription__Group_3_2__0 )? )
            {
            // InternalVideoGen.g:1727:1: ( ( rule__VideoDescription__Group_3_2__0 )? )
            // InternalVideoGen.g:1728:2: ( rule__VideoDescription__Group_3_2__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3_2()); 
            // InternalVideoGen.g:1729:2: ( rule__VideoDescription__Group_3_2__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==32) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalVideoGen.g:1729:3: rule__VideoDescription__Group_3_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__2__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__3"
    // InternalVideoGen.g:1737:1: rule__VideoDescription__Group_3__3 : rule__VideoDescription__Group_3__3__Impl rule__VideoDescription__Group_3__4 ;
    public final void rule__VideoDescription__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1741:1: ( rule__VideoDescription__Group_3__3__Impl rule__VideoDescription__Group_3__4 )
            // InternalVideoGen.g:1742:2: rule__VideoDescription__Group_3__3__Impl rule__VideoDescription__Group_3__4
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__3"


    // $ANTLR start "rule__VideoDescription__Group_3__3__Impl"
    // InternalVideoGen.g:1749:1: rule__VideoDescription__Group_3__3__Impl : ( ( rule__VideoDescription__Group_3_3__0 )? ) ;
    public final void rule__VideoDescription__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1753:1: ( ( ( rule__VideoDescription__Group_3_3__0 )? ) )
            // InternalVideoGen.g:1754:1: ( ( rule__VideoDescription__Group_3_3__0 )? )
            {
            // InternalVideoGen.g:1754:1: ( ( rule__VideoDescription__Group_3_3__0 )? )
            // InternalVideoGen.g:1755:2: ( rule__VideoDescription__Group_3_3__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3_3()); 
            // InternalVideoGen.g:1756:2: ( rule__VideoDescription__Group_3_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==33) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalVideoGen.g:1756:3: rule__VideoDescription__Group_3_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__3__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__4"
    // InternalVideoGen.g:1764:1: rule__VideoDescription__Group_3__4 : rule__VideoDescription__Group_3__4__Impl rule__VideoDescription__Group_3__5 ;
    public final void rule__VideoDescription__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1768:1: ( rule__VideoDescription__Group_3__4__Impl rule__VideoDescription__Group_3__5 )
            // InternalVideoGen.g:1769:2: rule__VideoDescription__Group_3__4__Impl rule__VideoDescription__Group_3__5
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__4"


    // $ANTLR start "rule__VideoDescription__Group_3__4__Impl"
    // InternalVideoGen.g:1776:1: rule__VideoDescription__Group_3__4__Impl : ( ( rule__VideoDescription__Group_3_4__0 )? ) ;
    public final void rule__VideoDescription__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1780:1: ( ( ( rule__VideoDescription__Group_3_4__0 )? ) )
            // InternalVideoGen.g:1781:1: ( ( rule__VideoDescription__Group_3_4__0 )? )
            {
            // InternalVideoGen.g:1781:1: ( ( rule__VideoDescription__Group_3_4__0 )? )
            // InternalVideoGen.g:1782:2: ( rule__VideoDescription__Group_3_4__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3_4()); 
            // InternalVideoGen.g:1783:2: ( rule__VideoDescription__Group_3_4__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==34) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalVideoGen.g:1783:3: rule__VideoDescription__Group_3_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__4__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__5"
    // InternalVideoGen.g:1791:1: rule__VideoDescription__Group_3__5 : rule__VideoDescription__Group_3__5__Impl rule__VideoDescription__Group_3__6 ;
    public final void rule__VideoDescription__Group_3__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1795:1: ( rule__VideoDescription__Group_3__5__Impl rule__VideoDescription__Group_3__6 )
            // InternalVideoGen.g:1796:2: rule__VideoDescription__Group_3__5__Impl rule__VideoDescription__Group_3__6
            {
            pushFollow(FOLLOW_17);
            rule__VideoDescription__Group_3__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__5"


    // $ANTLR start "rule__VideoDescription__Group_3__5__Impl"
    // InternalVideoGen.g:1803:1: rule__VideoDescription__Group_3__5__Impl : ( ( rule__VideoDescription__Group_3_5__0 )? ) ;
    public final void rule__VideoDescription__Group_3__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1807:1: ( ( ( rule__VideoDescription__Group_3_5__0 )? ) )
            // InternalVideoGen.g:1808:1: ( ( rule__VideoDescription__Group_3_5__0 )? )
            {
            // InternalVideoGen.g:1808:1: ( ( rule__VideoDescription__Group_3_5__0 )? )
            // InternalVideoGen.g:1809:2: ( rule__VideoDescription__Group_3_5__0 )?
            {
             before(grammarAccess.getVideoDescriptionAccess().getGroup_3_5()); 
            // InternalVideoGen.g:1810:2: ( rule__VideoDescription__Group_3_5__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==35) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalVideoGen.g:1810:3: rule__VideoDescription__Group_3_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoDescription__Group_3_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoDescriptionAccess().getGroup_3_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__5__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3__6"
    // InternalVideoGen.g:1818:1: rule__VideoDescription__Group_3__6 : rule__VideoDescription__Group_3__6__Impl ;
    public final void rule__VideoDescription__Group_3__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1822:1: ( rule__VideoDescription__Group_3__6__Impl )
            // InternalVideoGen.g:1823:2: rule__VideoDescription__Group_3__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__6"


    // $ANTLR start "rule__VideoDescription__Group_3__6__Impl"
    // InternalVideoGen.g:1829:1: rule__VideoDescription__Group_3__6__Impl : ( RULE_RIGHT_BRACKET ) ;
    public final void rule__VideoDescription__Group_3__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1833:1: ( ( RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:1834:1: ( RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:1834:1: ( RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:1835:2: RULE_RIGHT_BRACKET
            {
             before(grammarAccess.getVideoDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_6()); 
            match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3__6__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_1__0"
    // InternalVideoGen.g:1845:1: rule__VideoDescription__Group_3_1__0 : rule__VideoDescription__Group_3_1__0__Impl rule__VideoDescription__Group_3_1__1 ;
    public final void rule__VideoDescription__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1849:1: ( rule__VideoDescription__Group_3_1__0__Impl rule__VideoDescription__Group_3_1__1 )
            // InternalVideoGen.g:1850:2: rule__VideoDescription__Group_3_1__0__Impl rule__VideoDescription__Group_3_1__1
            {
            pushFollow(FOLLOW_18);
            rule__VideoDescription__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_1__0"


    // $ANTLR start "rule__VideoDescription__Group_3_1__0__Impl"
    // InternalVideoGen.g:1857:1: rule__VideoDescription__Group_3_1__0__Impl : ( 'duration' ) ;
    public final void rule__VideoDescription__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1861:1: ( ( 'duration' ) )
            // InternalVideoGen.g:1862:1: ( 'duration' )
            {
            // InternalVideoGen.g:1862:1: ( 'duration' )
            // InternalVideoGen.g:1863:2: 'duration'
            {
             before(grammarAccess.getVideoDescriptionAccess().getDurationKeyword_3_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getDurationKeyword_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_1__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_1__1"
    // InternalVideoGen.g:1872:1: rule__VideoDescription__Group_3_1__1 : rule__VideoDescription__Group_3_1__1__Impl ;
    public final void rule__VideoDescription__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1876:1: ( rule__VideoDescription__Group_3_1__1__Impl )
            // InternalVideoGen.g:1877:2: rule__VideoDescription__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_1__1"


    // $ANTLR start "rule__VideoDescription__Group_3_1__1__Impl"
    // InternalVideoGen.g:1883:1: rule__VideoDescription__Group_3_1__1__Impl : ( ( rule__VideoDescription__DurationAssignment_3_1_1 ) ) ;
    public final void rule__VideoDescription__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1887:1: ( ( ( rule__VideoDescription__DurationAssignment_3_1_1 ) ) )
            // InternalVideoGen.g:1888:1: ( ( rule__VideoDescription__DurationAssignment_3_1_1 ) )
            {
            // InternalVideoGen.g:1888:1: ( ( rule__VideoDescription__DurationAssignment_3_1_1 ) )
            // InternalVideoGen.g:1889:2: ( rule__VideoDescription__DurationAssignment_3_1_1 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getDurationAssignment_3_1_1()); 
            // InternalVideoGen.g:1890:2: ( rule__VideoDescription__DurationAssignment_3_1_1 )
            // InternalVideoGen.g:1890:3: rule__VideoDescription__DurationAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__DurationAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getDurationAssignment_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_1__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_2__0"
    // InternalVideoGen.g:1899:1: rule__VideoDescription__Group_3_2__0 : rule__VideoDescription__Group_3_2__0__Impl rule__VideoDescription__Group_3_2__1 ;
    public final void rule__VideoDescription__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1903:1: ( rule__VideoDescription__Group_3_2__0__Impl rule__VideoDescription__Group_3_2__1 )
            // InternalVideoGen.g:1904:2: rule__VideoDescription__Group_3_2__0__Impl rule__VideoDescription__Group_3_2__1
            {
            pushFollow(FOLLOW_18);
            rule__VideoDescription__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_2__0"


    // $ANTLR start "rule__VideoDescription__Group_3_2__0__Impl"
    // InternalVideoGen.g:1911:1: rule__VideoDescription__Group_3_2__0__Impl : ( 'probability' ) ;
    public final void rule__VideoDescription__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1915:1: ( ( 'probability' ) )
            // InternalVideoGen.g:1916:1: ( 'probability' )
            {
            // InternalVideoGen.g:1916:1: ( 'probability' )
            // InternalVideoGen.g:1917:2: 'probability'
            {
             before(grammarAccess.getVideoDescriptionAccess().getProbabilityKeyword_3_2_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getProbabilityKeyword_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_2__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_2__1"
    // InternalVideoGen.g:1926:1: rule__VideoDescription__Group_3_2__1 : rule__VideoDescription__Group_3_2__1__Impl ;
    public final void rule__VideoDescription__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1930:1: ( rule__VideoDescription__Group_3_2__1__Impl )
            // InternalVideoGen.g:1931:2: rule__VideoDescription__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_2__1"


    // $ANTLR start "rule__VideoDescription__Group_3_2__1__Impl"
    // InternalVideoGen.g:1937:1: rule__VideoDescription__Group_3_2__1__Impl : ( ( rule__VideoDescription__ProbabilityAssignment_3_2_1 ) ) ;
    public final void rule__VideoDescription__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1941:1: ( ( ( rule__VideoDescription__ProbabilityAssignment_3_2_1 ) ) )
            // InternalVideoGen.g:1942:1: ( ( rule__VideoDescription__ProbabilityAssignment_3_2_1 ) )
            {
            // InternalVideoGen.g:1942:1: ( ( rule__VideoDescription__ProbabilityAssignment_3_2_1 ) )
            // InternalVideoGen.g:1943:2: ( rule__VideoDescription__ProbabilityAssignment_3_2_1 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getProbabilityAssignment_3_2_1()); 
            // InternalVideoGen.g:1944:2: ( rule__VideoDescription__ProbabilityAssignment_3_2_1 )
            // InternalVideoGen.g:1944:3: rule__VideoDescription__ProbabilityAssignment_3_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__ProbabilityAssignment_3_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getProbabilityAssignment_3_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_2__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_3__0"
    // InternalVideoGen.g:1953:1: rule__VideoDescription__Group_3_3__0 : rule__VideoDescription__Group_3_3__0__Impl rule__VideoDescription__Group_3_3__1 ;
    public final void rule__VideoDescription__Group_3_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1957:1: ( rule__VideoDescription__Group_3_3__0__Impl rule__VideoDescription__Group_3_3__1 )
            // InternalVideoGen.g:1958:2: rule__VideoDescription__Group_3_3__0__Impl rule__VideoDescription__Group_3_3__1
            {
            pushFollow(FOLLOW_10);
            rule__VideoDescription__Group_3_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_3__0"


    // $ANTLR start "rule__VideoDescription__Group_3_3__0__Impl"
    // InternalVideoGen.g:1965:1: rule__VideoDescription__Group_3_3__0__Impl : ( 'description' ) ;
    public final void rule__VideoDescription__Group_3_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1969:1: ( ( 'description' ) )
            // InternalVideoGen.g:1970:1: ( 'description' )
            {
            // InternalVideoGen.g:1970:1: ( 'description' )
            // InternalVideoGen.g:1971:2: 'description'
            {
             before(grammarAccess.getVideoDescriptionAccess().getDescriptionKeyword_3_3_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getDescriptionKeyword_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_3__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_3__1"
    // InternalVideoGen.g:1980:1: rule__VideoDescription__Group_3_3__1 : rule__VideoDescription__Group_3_3__1__Impl ;
    public final void rule__VideoDescription__Group_3_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1984:1: ( rule__VideoDescription__Group_3_3__1__Impl )
            // InternalVideoGen.g:1985:2: rule__VideoDescription__Group_3_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_3__1"


    // $ANTLR start "rule__VideoDescription__Group_3_3__1__Impl"
    // InternalVideoGen.g:1991:1: rule__VideoDescription__Group_3_3__1__Impl : ( ( rule__VideoDescription__DescriptionAssignment_3_3_1 ) ) ;
    public final void rule__VideoDescription__Group_3_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:1995:1: ( ( ( rule__VideoDescription__DescriptionAssignment_3_3_1 ) ) )
            // InternalVideoGen.g:1996:1: ( ( rule__VideoDescription__DescriptionAssignment_3_3_1 ) )
            {
            // InternalVideoGen.g:1996:1: ( ( rule__VideoDescription__DescriptionAssignment_3_3_1 ) )
            // InternalVideoGen.g:1997:2: ( rule__VideoDescription__DescriptionAssignment_3_3_1 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getDescriptionAssignment_3_3_1()); 
            // InternalVideoGen.g:1998:2: ( rule__VideoDescription__DescriptionAssignment_3_3_1 )
            // InternalVideoGen.g:1998:3: rule__VideoDescription__DescriptionAssignment_3_3_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__DescriptionAssignment_3_3_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getDescriptionAssignment_3_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_3__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_4__0"
    // InternalVideoGen.g:2007:1: rule__VideoDescription__Group_3_4__0 : rule__VideoDescription__Group_3_4__0__Impl rule__VideoDescription__Group_3_4__1 ;
    public final void rule__VideoDescription__Group_3_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2011:1: ( rule__VideoDescription__Group_3_4__0__Impl rule__VideoDescription__Group_3_4__1 )
            // InternalVideoGen.g:2012:2: rule__VideoDescription__Group_3_4__0__Impl rule__VideoDescription__Group_3_4__1
            {
            pushFollow(FOLLOW_19);
            rule__VideoDescription__Group_3_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_4__0"


    // $ANTLR start "rule__VideoDescription__Group_3_4__0__Impl"
    // InternalVideoGen.g:2019:1: rule__VideoDescription__Group_3_4__0__Impl : ( 'filter' ) ;
    public final void rule__VideoDescription__Group_3_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2023:1: ( ( 'filter' ) )
            // InternalVideoGen.g:2024:1: ( 'filter' )
            {
            // InternalVideoGen.g:2024:1: ( 'filter' )
            // InternalVideoGen.g:2025:2: 'filter'
            {
             before(grammarAccess.getVideoDescriptionAccess().getFilterKeyword_3_4_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getFilterKeyword_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_4__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_4__1"
    // InternalVideoGen.g:2034:1: rule__VideoDescription__Group_3_4__1 : rule__VideoDescription__Group_3_4__1__Impl ;
    public final void rule__VideoDescription__Group_3_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2038:1: ( rule__VideoDescription__Group_3_4__1__Impl )
            // InternalVideoGen.g:2039:2: rule__VideoDescription__Group_3_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_4__1"


    // $ANTLR start "rule__VideoDescription__Group_3_4__1__Impl"
    // InternalVideoGen.g:2045:1: rule__VideoDescription__Group_3_4__1__Impl : ( ( rule__VideoDescription__FilterAssignment_3_4_1 ) ) ;
    public final void rule__VideoDescription__Group_3_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2049:1: ( ( ( rule__VideoDescription__FilterAssignment_3_4_1 ) ) )
            // InternalVideoGen.g:2050:1: ( ( rule__VideoDescription__FilterAssignment_3_4_1 ) )
            {
            // InternalVideoGen.g:2050:1: ( ( rule__VideoDescription__FilterAssignment_3_4_1 ) )
            // InternalVideoGen.g:2051:2: ( rule__VideoDescription__FilterAssignment_3_4_1 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getFilterAssignment_3_4_1()); 
            // InternalVideoGen.g:2052:2: ( rule__VideoDescription__FilterAssignment_3_4_1 )
            // InternalVideoGen.g:2052:3: rule__VideoDescription__FilterAssignment_3_4_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__FilterAssignment_3_4_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getFilterAssignment_3_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_4__1__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_5__0"
    // InternalVideoGen.g:2061:1: rule__VideoDescription__Group_3_5__0 : rule__VideoDescription__Group_3_5__0__Impl rule__VideoDescription__Group_3_5__1 ;
    public final void rule__VideoDescription__Group_3_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2065:1: ( rule__VideoDescription__Group_3_5__0__Impl rule__VideoDescription__Group_3_5__1 )
            // InternalVideoGen.g:2066:2: rule__VideoDescription__Group_3_5__0__Impl rule__VideoDescription__Group_3_5__1
            {
            pushFollow(FOLLOW_4);
            rule__VideoDescription__Group_3_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_5__0"


    // $ANTLR start "rule__VideoDescription__Group_3_5__0__Impl"
    // InternalVideoGen.g:2073:1: rule__VideoDescription__Group_3_5__0__Impl : ( 'text' ) ;
    public final void rule__VideoDescription__Group_3_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2077:1: ( ( 'text' ) )
            // InternalVideoGen.g:2078:1: ( 'text' )
            {
            // InternalVideoGen.g:2078:1: ( 'text' )
            // InternalVideoGen.g:2079:2: 'text'
            {
             before(grammarAccess.getVideoDescriptionAccess().getTextKeyword_3_5_0()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getTextKeyword_3_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_5__0__Impl"


    // $ANTLR start "rule__VideoDescription__Group_3_5__1"
    // InternalVideoGen.g:2088:1: rule__VideoDescription__Group_3_5__1 : rule__VideoDescription__Group_3_5__1__Impl ;
    public final void rule__VideoDescription__Group_3_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2092:1: ( rule__VideoDescription__Group_3_5__1__Impl )
            // InternalVideoGen.g:2093:2: rule__VideoDescription__Group_3_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__Group_3_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_5__1"


    // $ANTLR start "rule__VideoDescription__Group_3_5__1__Impl"
    // InternalVideoGen.g:2099:1: rule__VideoDescription__Group_3_5__1__Impl : ( ( rule__VideoDescription__TextAssignment_3_5_1 ) ) ;
    public final void rule__VideoDescription__Group_3_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2103:1: ( ( ( rule__VideoDescription__TextAssignment_3_5_1 ) ) )
            // InternalVideoGen.g:2104:1: ( ( rule__VideoDescription__TextAssignment_3_5_1 ) )
            {
            // InternalVideoGen.g:2104:1: ( ( rule__VideoDescription__TextAssignment_3_5_1 ) )
            // InternalVideoGen.g:2105:2: ( rule__VideoDescription__TextAssignment_3_5_1 )
            {
             before(grammarAccess.getVideoDescriptionAccess().getTextAssignment_3_5_1()); 
            // InternalVideoGen.g:2106:2: ( rule__VideoDescription__TextAssignment_3_5_1 )
            // InternalVideoGen.g:2106:3: rule__VideoDescription__TextAssignment_3_5_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoDescription__TextAssignment_3_5_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoDescriptionAccess().getTextAssignment_3_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__Group_3_5__1__Impl"


    // $ANTLR start "rule__VideoText__Group__0"
    // InternalVideoGen.g:2115:1: rule__VideoText__Group__0 : rule__VideoText__Group__0__Impl rule__VideoText__Group__1 ;
    public final void rule__VideoText__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2119:1: ( rule__VideoText__Group__0__Impl rule__VideoText__Group__1 )
            // InternalVideoGen.g:2120:2: rule__VideoText__Group__0__Impl rule__VideoText__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__VideoText__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__0"


    // $ANTLR start "rule__VideoText__Group__0__Impl"
    // InternalVideoGen.g:2127:1: rule__VideoText__Group__0__Impl : ( RULE_LEFT_BRACKET ) ;
    public final void rule__VideoText__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2131:1: ( ( RULE_LEFT_BRACKET ) )
            // InternalVideoGen.g:2132:1: ( RULE_LEFT_BRACKET )
            {
            // InternalVideoGen.g:2132:1: ( RULE_LEFT_BRACKET )
            // InternalVideoGen.g:2133:2: RULE_LEFT_BRACKET
            {
             before(grammarAccess.getVideoTextAccess().getLEFT_BRACKETTerminalRuleCall_0()); 
            match(input,RULE_LEFT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getLEFT_BRACKETTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__0__Impl"


    // $ANTLR start "rule__VideoText__Group__1"
    // InternalVideoGen.g:2142:1: rule__VideoText__Group__1 : rule__VideoText__Group__1__Impl rule__VideoText__Group__2 ;
    public final void rule__VideoText__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2146:1: ( rule__VideoText__Group__1__Impl rule__VideoText__Group__2 )
            // InternalVideoGen.g:2147:2: rule__VideoText__Group__1__Impl rule__VideoText__Group__2
            {
            pushFollow(FOLLOW_10);
            rule__VideoText__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__1"


    // $ANTLR start "rule__VideoText__Group__1__Impl"
    // InternalVideoGen.g:2154:1: rule__VideoText__Group__1__Impl : ( 'content' ) ;
    public final void rule__VideoText__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2158:1: ( ( 'content' ) )
            // InternalVideoGen.g:2159:1: ( 'content' )
            {
            // InternalVideoGen.g:2159:1: ( 'content' )
            // InternalVideoGen.g:2160:2: 'content'
            {
             before(grammarAccess.getVideoTextAccess().getContentKeyword_1()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getContentKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__1__Impl"


    // $ANTLR start "rule__VideoText__Group__2"
    // InternalVideoGen.g:2169:1: rule__VideoText__Group__2 : rule__VideoText__Group__2__Impl rule__VideoText__Group__3 ;
    public final void rule__VideoText__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2173:1: ( rule__VideoText__Group__2__Impl rule__VideoText__Group__3 )
            // InternalVideoGen.g:2174:2: rule__VideoText__Group__2__Impl rule__VideoText__Group__3
            {
            pushFollow(FOLLOW_21);
            rule__VideoText__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__2"


    // $ANTLR start "rule__VideoText__Group__2__Impl"
    // InternalVideoGen.g:2181:1: rule__VideoText__Group__2__Impl : ( ( rule__VideoText__ContentAssignment_2 ) ) ;
    public final void rule__VideoText__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2185:1: ( ( ( rule__VideoText__ContentAssignment_2 ) ) )
            // InternalVideoGen.g:2186:1: ( ( rule__VideoText__ContentAssignment_2 ) )
            {
            // InternalVideoGen.g:2186:1: ( ( rule__VideoText__ContentAssignment_2 ) )
            // InternalVideoGen.g:2187:2: ( rule__VideoText__ContentAssignment_2 )
            {
             before(grammarAccess.getVideoTextAccess().getContentAssignment_2()); 
            // InternalVideoGen.g:2188:2: ( rule__VideoText__ContentAssignment_2 )
            // InternalVideoGen.g:2188:3: rule__VideoText__ContentAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__ContentAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVideoTextAccess().getContentAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__2__Impl"


    // $ANTLR start "rule__VideoText__Group__3"
    // InternalVideoGen.g:2196:1: rule__VideoText__Group__3 : rule__VideoText__Group__3__Impl rule__VideoText__Group__4 ;
    public final void rule__VideoText__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2200:1: ( rule__VideoText__Group__3__Impl rule__VideoText__Group__4 )
            // InternalVideoGen.g:2201:2: rule__VideoText__Group__3__Impl rule__VideoText__Group__4
            {
            pushFollow(FOLLOW_22);
            rule__VideoText__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__3"


    // $ANTLR start "rule__VideoText__Group__3__Impl"
    // InternalVideoGen.g:2208:1: rule__VideoText__Group__3__Impl : ( 'position' ) ;
    public final void rule__VideoText__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2212:1: ( ( 'position' ) )
            // InternalVideoGen.g:2213:1: ( 'position' )
            {
            // InternalVideoGen.g:2213:1: ( 'position' )
            // InternalVideoGen.g:2214:2: 'position'
            {
             before(grammarAccess.getVideoTextAccess().getPositionKeyword_3()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getPositionKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__3__Impl"


    // $ANTLR start "rule__VideoText__Group__4"
    // InternalVideoGen.g:2223:1: rule__VideoText__Group__4 : rule__VideoText__Group__4__Impl rule__VideoText__Group__5 ;
    public final void rule__VideoText__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2227:1: ( rule__VideoText__Group__4__Impl rule__VideoText__Group__5 )
            // InternalVideoGen.g:2228:2: rule__VideoText__Group__4__Impl rule__VideoText__Group__5
            {
            pushFollow(FOLLOW_23);
            rule__VideoText__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__4"


    // $ANTLR start "rule__VideoText__Group__4__Impl"
    // InternalVideoGen.g:2235:1: rule__VideoText__Group__4__Impl : ( ( rule__VideoText__PositionAssignment_4 ) ) ;
    public final void rule__VideoText__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2239:1: ( ( ( rule__VideoText__PositionAssignment_4 ) ) )
            // InternalVideoGen.g:2240:1: ( ( rule__VideoText__PositionAssignment_4 ) )
            {
            // InternalVideoGen.g:2240:1: ( ( rule__VideoText__PositionAssignment_4 ) )
            // InternalVideoGen.g:2241:2: ( rule__VideoText__PositionAssignment_4 )
            {
             before(grammarAccess.getVideoTextAccess().getPositionAssignment_4()); 
            // InternalVideoGen.g:2242:2: ( rule__VideoText__PositionAssignment_4 )
            // InternalVideoGen.g:2242:3: rule__VideoText__PositionAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__PositionAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getVideoTextAccess().getPositionAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__4__Impl"


    // $ANTLR start "rule__VideoText__Group__5"
    // InternalVideoGen.g:2250:1: rule__VideoText__Group__5 : rule__VideoText__Group__5__Impl rule__VideoText__Group__6 ;
    public final void rule__VideoText__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2254:1: ( rule__VideoText__Group__5__Impl rule__VideoText__Group__6 )
            // InternalVideoGen.g:2255:2: rule__VideoText__Group__5__Impl rule__VideoText__Group__6
            {
            pushFollow(FOLLOW_23);
            rule__VideoText__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__5"


    // $ANTLR start "rule__VideoText__Group__5__Impl"
    // InternalVideoGen.g:2262:1: rule__VideoText__Group__5__Impl : ( ( rule__VideoText__Group_5__0 )? ) ;
    public final void rule__VideoText__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2266:1: ( ( ( rule__VideoText__Group_5__0 )? ) )
            // InternalVideoGen.g:2267:1: ( ( rule__VideoText__Group_5__0 )? )
            {
            // InternalVideoGen.g:2267:1: ( ( rule__VideoText__Group_5__0 )? )
            // InternalVideoGen.g:2268:2: ( rule__VideoText__Group_5__0 )?
            {
             before(grammarAccess.getVideoTextAccess().getGroup_5()); 
            // InternalVideoGen.g:2269:2: ( rule__VideoText__Group_5__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==38) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalVideoGen.g:2269:3: rule__VideoText__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoText__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoTextAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__5__Impl"


    // $ANTLR start "rule__VideoText__Group__6"
    // InternalVideoGen.g:2277:1: rule__VideoText__Group__6 : rule__VideoText__Group__6__Impl rule__VideoText__Group__7 ;
    public final void rule__VideoText__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2281:1: ( rule__VideoText__Group__6__Impl rule__VideoText__Group__7 )
            // InternalVideoGen.g:2282:2: rule__VideoText__Group__6__Impl rule__VideoText__Group__7
            {
            pushFollow(FOLLOW_23);
            rule__VideoText__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__6"


    // $ANTLR start "rule__VideoText__Group__6__Impl"
    // InternalVideoGen.g:2289:1: rule__VideoText__Group__6__Impl : ( ( rule__VideoText__Group_6__0 )? ) ;
    public final void rule__VideoText__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2293:1: ( ( ( rule__VideoText__Group_6__0 )? ) )
            // InternalVideoGen.g:2294:1: ( ( rule__VideoText__Group_6__0 )? )
            {
            // InternalVideoGen.g:2294:1: ( ( rule__VideoText__Group_6__0 )? )
            // InternalVideoGen.g:2295:2: ( rule__VideoText__Group_6__0 )?
            {
             before(grammarAccess.getVideoTextAccess().getGroup_6()); 
            // InternalVideoGen.g:2296:2: ( rule__VideoText__Group_6__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==39) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalVideoGen.g:2296:3: rule__VideoText__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VideoText__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVideoTextAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__6__Impl"


    // $ANTLR start "rule__VideoText__Group__7"
    // InternalVideoGen.g:2304:1: rule__VideoText__Group__7 : rule__VideoText__Group__7__Impl ;
    public final void rule__VideoText__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2308:1: ( rule__VideoText__Group__7__Impl )
            // InternalVideoGen.g:2309:2: rule__VideoText__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__7"


    // $ANTLR start "rule__VideoText__Group__7__Impl"
    // InternalVideoGen.g:2315:1: rule__VideoText__Group__7__Impl : ( RULE_RIGHT_BRACKET ) ;
    public final void rule__VideoText__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2319:1: ( ( RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:2320:1: ( RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:2320:1: ( RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:2321:2: RULE_RIGHT_BRACKET
            {
             before(grammarAccess.getVideoTextAccess().getRIGHT_BRACKETTerminalRuleCall_7()); 
            match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getRIGHT_BRACKETTerminalRuleCall_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group__7__Impl"


    // $ANTLR start "rule__VideoText__Group_5__0"
    // InternalVideoGen.g:2331:1: rule__VideoText__Group_5__0 : rule__VideoText__Group_5__0__Impl rule__VideoText__Group_5__1 ;
    public final void rule__VideoText__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2335:1: ( rule__VideoText__Group_5__0__Impl rule__VideoText__Group_5__1 )
            // InternalVideoGen.g:2336:2: rule__VideoText__Group_5__0__Impl rule__VideoText__Group_5__1
            {
            pushFollow(FOLLOW_10);
            rule__VideoText__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_5__0"


    // $ANTLR start "rule__VideoText__Group_5__0__Impl"
    // InternalVideoGen.g:2343:1: rule__VideoText__Group_5__0__Impl : ( 'color' ) ;
    public final void rule__VideoText__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2347:1: ( ( 'color' ) )
            // InternalVideoGen.g:2348:1: ( 'color' )
            {
            // InternalVideoGen.g:2348:1: ( 'color' )
            // InternalVideoGen.g:2349:2: 'color'
            {
             before(grammarAccess.getVideoTextAccess().getColorKeyword_5_0()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getColorKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_5__0__Impl"


    // $ANTLR start "rule__VideoText__Group_5__1"
    // InternalVideoGen.g:2358:1: rule__VideoText__Group_5__1 : rule__VideoText__Group_5__1__Impl ;
    public final void rule__VideoText__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2362:1: ( rule__VideoText__Group_5__1__Impl )
            // InternalVideoGen.g:2363:2: rule__VideoText__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_5__1"


    // $ANTLR start "rule__VideoText__Group_5__1__Impl"
    // InternalVideoGen.g:2369:1: rule__VideoText__Group_5__1__Impl : ( ( rule__VideoText__ColorAssignment_5_1 ) ) ;
    public final void rule__VideoText__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2373:1: ( ( ( rule__VideoText__ColorAssignment_5_1 ) ) )
            // InternalVideoGen.g:2374:1: ( ( rule__VideoText__ColorAssignment_5_1 ) )
            {
            // InternalVideoGen.g:2374:1: ( ( rule__VideoText__ColorAssignment_5_1 ) )
            // InternalVideoGen.g:2375:2: ( rule__VideoText__ColorAssignment_5_1 )
            {
             before(grammarAccess.getVideoTextAccess().getColorAssignment_5_1()); 
            // InternalVideoGen.g:2376:2: ( rule__VideoText__ColorAssignment_5_1 )
            // InternalVideoGen.g:2376:3: rule__VideoText__ColorAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__ColorAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoTextAccess().getColorAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_5__1__Impl"


    // $ANTLR start "rule__VideoText__Group_6__0"
    // InternalVideoGen.g:2385:1: rule__VideoText__Group_6__0 : rule__VideoText__Group_6__0__Impl rule__VideoText__Group_6__1 ;
    public final void rule__VideoText__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2389:1: ( rule__VideoText__Group_6__0__Impl rule__VideoText__Group_6__1 )
            // InternalVideoGen.g:2390:2: rule__VideoText__Group_6__0__Impl rule__VideoText__Group_6__1
            {
            pushFollow(FOLLOW_18);
            rule__VideoText__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VideoText__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_6__0"


    // $ANTLR start "rule__VideoText__Group_6__0__Impl"
    // InternalVideoGen.g:2397:1: rule__VideoText__Group_6__0__Impl : ( 'size' ) ;
    public final void rule__VideoText__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2401:1: ( ( 'size' ) )
            // InternalVideoGen.g:2402:1: ( 'size' )
            {
            // InternalVideoGen.g:2402:1: ( 'size' )
            // InternalVideoGen.g:2403:2: 'size'
            {
             before(grammarAccess.getVideoTextAccess().getSizeKeyword_6_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getSizeKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_6__0__Impl"


    // $ANTLR start "rule__VideoText__Group_6__1"
    // InternalVideoGen.g:2412:1: rule__VideoText__Group_6__1 : rule__VideoText__Group_6__1__Impl ;
    public final void rule__VideoText__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2416:1: ( rule__VideoText__Group_6__1__Impl )
            // InternalVideoGen.g:2417:2: rule__VideoText__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_6__1"


    // $ANTLR start "rule__VideoText__Group_6__1__Impl"
    // InternalVideoGen.g:2423:1: rule__VideoText__Group_6__1__Impl : ( ( rule__VideoText__SizeAssignment_6_1 ) ) ;
    public final void rule__VideoText__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2427:1: ( ( ( rule__VideoText__SizeAssignment_6_1 ) ) )
            // InternalVideoGen.g:2428:1: ( ( rule__VideoText__SizeAssignment_6_1 ) )
            {
            // InternalVideoGen.g:2428:1: ( ( rule__VideoText__SizeAssignment_6_1 ) )
            // InternalVideoGen.g:2429:2: ( rule__VideoText__SizeAssignment_6_1 )
            {
             before(grammarAccess.getVideoTextAccess().getSizeAssignment_6_1()); 
            // InternalVideoGen.g:2430:2: ( rule__VideoText__SizeAssignment_6_1 )
            // InternalVideoGen.g:2430:3: rule__VideoText__SizeAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__VideoText__SizeAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getVideoTextAccess().getSizeAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__Group_6__1__Impl"


    // $ANTLR start "rule__BlackWhiteFilter__Group__0"
    // InternalVideoGen.g:2439:1: rule__BlackWhiteFilter__Group__0 : rule__BlackWhiteFilter__Group__0__Impl rule__BlackWhiteFilter__Group__1 ;
    public final void rule__BlackWhiteFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2443:1: ( rule__BlackWhiteFilter__Group__0__Impl rule__BlackWhiteFilter__Group__1 )
            // InternalVideoGen.g:2444:2: rule__BlackWhiteFilter__Group__0__Impl rule__BlackWhiteFilter__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__BlackWhiteFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BlackWhiteFilter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BlackWhiteFilter__Group__0"


    // $ANTLR start "rule__BlackWhiteFilter__Group__0__Impl"
    // InternalVideoGen.g:2451:1: rule__BlackWhiteFilter__Group__0__Impl : ( () ) ;
    public final void rule__BlackWhiteFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2455:1: ( ( () ) )
            // InternalVideoGen.g:2456:1: ( () )
            {
            // InternalVideoGen.g:2456:1: ( () )
            // InternalVideoGen.g:2457:2: ()
            {
             before(grammarAccess.getBlackWhiteFilterAccess().getBlackWhiteFilterAction_0()); 
            // InternalVideoGen.g:2458:2: ()
            // InternalVideoGen.g:2458:3: 
            {
            }

             after(grammarAccess.getBlackWhiteFilterAccess().getBlackWhiteFilterAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BlackWhiteFilter__Group__0__Impl"


    // $ANTLR start "rule__BlackWhiteFilter__Group__1"
    // InternalVideoGen.g:2466:1: rule__BlackWhiteFilter__Group__1 : rule__BlackWhiteFilter__Group__1__Impl ;
    public final void rule__BlackWhiteFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2470:1: ( rule__BlackWhiteFilter__Group__1__Impl )
            // InternalVideoGen.g:2471:2: rule__BlackWhiteFilter__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BlackWhiteFilter__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BlackWhiteFilter__Group__1"


    // $ANTLR start "rule__BlackWhiteFilter__Group__1__Impl"
    // InternalVideoGen.g:2477:1: rule__BlackWhiteFilter__Group__1__Impl : ( 'b&w' ) ;
    public final void rule__BlackWhiteFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2481:1: ( ( 'b&w' ) )
            // InternalVideoGen.g:2482:1: ( 'b&w' )
            {
            // InternalVideoGen.g:2482:1: ( 'b&w' )
            // InternalVideoGen.g:2483:2: 'b&w'
            {
             before(grammarAccess.getBlackWhiteFilterAccess().getBWKeyword_1()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getBlackWhiteFilterAccess().getBWKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BlackWhiteFilter__Group__1__Impl"


    // $ANTLR start "rule__NegateFilter__Group__0"
    // InternalVideoGen.g:2493:1: rule__NegateFilter__Group__0 : rule__NegateFilter__Group__0__Impl rule__NegateFilter__Group__1 ;
    public final void rule__NegateFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2497:1: ( rule__NegateFilter__Group__0__Impl rule__NegateFilter__Group__1 )
            // InternalVideoGen.g:2498:2: rule__NegateFilter__Group__0__Impl rule__NegateFilter__Group__1
            {
            pushFollow(FOLLOW_24);
            rule__NegateFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NegateFilter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NegateFilter__Group__0"


    // $ANTLR start "rule__NegateFilter__Group__0__Impl"
    // InternalVideoGen.g:2505:1: rule__NegateFilter__Group__0__Impl : ( () ) ;
    public final void rule__NegateFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2509:1: ( ( () ) )
            // InternalVideoGen.g:2510:1: ( () )
            {
            // InternalVideoGen.g:2510:1: ( () )
            // InternalVideoGen.g:2511:2: ()
            {
             before(grammarAccess.getNegateFilterAccess().getNegateFilterAction_0()); 
            // InternalVideoGen.g:2512:2: ()
            // InternalVideoGen.g:2512:3: 
            {
            }

             after(grammarAccess.getNegateFilterAccess().getNegateFilterAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NegateFilter__Group__0__Impl"


    // $ANTLR start "rule__NegateFilter__Group__1"
    // InternalVideoGen.g:2520:1: rule__NegateFilter__Group__1 : rule__NegateFilter__Group__1__Impl ;
    public final void rule__NegateFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2524:1: ( rule__NegateFilter__Group__1__Impl )
            // InternalVideoGen.g:2525:2: rule__NegateFilter__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NegateFilter__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NegateFilter__Group__1"


    // $ANTLR start "rule__NegateFilter__Group__1__Impl"
    // InternalVideoGen.g:2531:1: rule__NegateFilter__Group__1__Impl : ( 'negate' ) ;
    public final void rule__NegateFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2535:1: ( ( 'negate' ) )
            // InternalVideoGen.g:2536:1: ( 'negate' )
            {
            // InternalVideoGen.g:2536:1: ( 'negate' )
            // InternalVideoGen.g:2537:2: 'negate'
            {
             before(grammarAccess.getNegateFilterAccess().getNegateKeyword_1()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getNegateFilterAccess().getNegateKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NegateFilter__Group__1__Impl"


    // $ANTLR start "rule__FlipFilter__Group__0"
    // InternalVideoGen.g:2547:1: rule__FlipFilter__Group__0 : rule__FlipFilter__Group__0__Impl rule__FlipFilter__Group__1 ;
    public final void rule__FlipFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2551:1: ( rule__FlipFilter__Group__0__Impl rule__FlipFilter__Group__1 )
            // InternalVideoGen.g:2552:2: rule__FlipFilter__Group__0__Impl rule__FlipFilter__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__FlipFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FlipFilter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__Group__0"


    // $ANTLR start "rule__FlipFilter__Group__0__Impl"
    // InternalVideoGen.g:2559:1: rule__FlipFilter__Group__0__Impl : ( 'flip' ) ;
    public final void rule__FlipFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2563:1: ( ( 'flip' ) )
            // InternalVideoGen.g:2564:1: ( 'flip' )
            {
            // InternalVideoGen.g:2564:1: ( 'flip' )
            // InternalVideoGen.g:2565:2: 'flip'
            {
             before(grammarAccess.getFlipFilterAccess().getFlipKeyword_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getFlipFilterAccess().getFlipKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__Group__0__Impl"


    // $ANTLR start "rule__FlipFilter__Group__1"
    // InternalVideoGen.g:2574:1: rule__FlipFilter__Group__1 : rule__FlipFilter__Group__1__Impl ;
    public final void rule__FlipFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2578:1: ( rule__FlipFilter__Group__1__Impl )
            // InternalVideoGen.g:2579:2: rule__FlipFilter__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FlipFilter__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__Group__1"


    // $ANTLR start "rule__FlipFilter__Group__1__Impl"
    // InternalVideoGen.g:2585:1: rule__FlipFilter__Group__1__Impl : ( ( rule__FlipFilter__OrientationAssignment_1 ) ) ;
    public final void rule__FlipFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2589:1: ( ( ( rule__FlipFilter__OrientationAssignment_1 ) ) )
            // InternalVideoGen.g:2590:1: ( ( rule__FlipFilter__OrientationAssignment_1 ) )
            {
            // InternalVideoGen.g:2590:1: ( ( rule__FlipFilter__OrientationAssignment_1 ) )
            // InternalVideoGen.g:2591:2: ( rule__FlipFilter__OrientationAssignment_1 )
            {
             before(grammarAccess.getFlipFilterAccess().getOrientationAssignment_1()); 
            // InternalVideoGen.g:2592:2: ( rule__FlipFilter__OrientationAssignment_1 )
            // InternalVideoGen.g:2592:3: rule__FlipFilter__OrientationAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FlipFilter__OrientationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFlipFilterAccess().getOrientationAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__Group__1__Impl"


    // $ANTLR start "rule__VideoGeneratorModel__InformationAssignment_0"
    // InternalVideoGen.g:2601:1: rule__VideoGeneratorModel__InformationAssignment_0 : ( ruleVideoGenInformation ) ;
    public final void rule__VideoGeneratorModel__InformationAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2605:1: ( ( ruleVideoGenInformation ) )
            // InternalVideoGen.g:2606:2: ( ruleVideoGenInformation )
            {
            // InternalVideoGen.g:2606:2: ( ruleVideoGenInformation )
            // InternalVideoGen.g:2607:3: ruleVideoGenInformation
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getInformationVideoGenInformationParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVideoGenInformation();

            state._fsp--;

             after(grammarAccess.getVideoGeneratorModelAccess().getInformationVideoGenInformationParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__InformationAssignment_0"


    // $ANTLR start "rule__VideoGeneratorModel__MediasAssignment_3"
    // InternalVideoGen.g:2616:1: rule__VideoGeneratorModel__MediasAssignment_3 : ( ruleMedia ) ;
    public final void rule__VideoGeneratorModel__MediasAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2620:1: ( ( ruleMedia ) )
            // InternalVideoGen.g:2621:2: ( ruleMedia )
            {
            // InternalVideoGen.g:2621:2: ( ruleMedia )
            // InternalVideoGen.g:2622:3: ruleMedia
            {
             before(grammarAccess.getVideoGeneratorModelAccess().getMediasMediaParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleMedia();

            state._fsp--;

             after(grammarAccess.getVideoGeneratorModelAccess().getMediasMediaParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGeneratorModel__MediasAssignment_3"


    // $ANTLR start "rule__VideoGenInformation__AuthorNameAssignment_1_1"
    // InternalVideoGen.g:2631:1: rule__VideoGenInformation__AuthorNameAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__VideoGenInformation__AuthorNameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2635:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2636:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2636:2: ( RULE_STRING )
            // InternalVideoGen.g:2637:3: RULE_STRING
            {
             before(grammarAccess.getVideoGenInformationAccess().getAuthorNameSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getAuthorNameSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__AuthorNameAssignment_1_1"


    // $ANTLR start "rule__VideoGenInformation__VersionAssignment_2_1"
    // InternalVideoGen.g:2646:1: rule__VideoGenInformation__VersionAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__VideoGenInformation__VersionAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2650:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2651:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2651:2: ( RULE_STRING )
            // InternalVideoGen.g:2652:3: RULE_STRING
            {
             before(grammarAccess.getVideoGenInformationAccess().getVersionSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getVersionSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__VersionAssignment_2_1"


    // $ANTLR start "rule__VideoGenInformation__CreationDateAssignment_3_1"
    // InternalVideoGen.g:2661:1: rule__VideoGenInformation__CreationDateAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__VideoGenInformation__CreationDateAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2665:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2666:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2666:2: ( RULE_STRING )
            // InternalVideoGen.g:2667:3: RULE_STRING
            {
             before(grammarAccess.getVideoGenInformationAccess().getCreationDateSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoGenInformationAccess().getCreationDateSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoGenInformation__CreationDateAssignment_3_1"


    // $ANTLR start "rule__MandatoryMedia__DescriptionAssignment_1"
    // InternalVideoGen.g:2676:1: rule__MandatoryMedia__DescriptionAssignment_1 : ( ruleMediaDescription ) ;
    public final void rule__MandatoryMedia__DescriptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2680:1: ( ( ruleMediaDescription ) )
            // InternalVideoGen.g:2681:2: ( ruleMediaDescription )
            {
            // InternalVideoGen.g:2681:2: ( ruleMediaDescription )
            // InternalVideoGen.g:2682:3: ruleMediaDescription
            {
             before(grammarAccess.getMandatoryMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMediaDescription();

            state._fsp--;

             after(grammarAccess.getMandatoryMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MandatoryMedia__DescriptionAssignment_1"


    // $ANTLR start "rule__OptionalMedia__DescriptionAssignment_1"
    // InternalVideoGen.g:2691:1: rule__OptionalMedia__DescriptionAssignment_1 : ( ruleMediaDescription ) ;
    public final void rule__OptionalMedia__DescriptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2695:1: ( ( ruleMediaDescription ) )
            // InternalVideoGen.g:2696:2: ( ruleMediaDescription )
            {
            // InternalVideoGen.g:2696:2: ( ruleMediaDescription )
            // InternalVideoGen.g:2697:3: ruleMediaDescription
            {
             before(grammarAccess.getOptionalMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMediaDescription();

            state._fsp--;

             after(grammarAccess.getOptionalMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionalMedia__DescriptionAssignment_1"


    // $ANTLR start "rule__AlternativesMedia__IdAssignment_1"
    // InternalVideoGen.g:2706:1: rule__AlternativesMedia__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__AlternativesMedia__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2710:1: ( ( RULE_ID ) )
            // InternalVideoGen.g:2711:2: ( RULE_ID )
            {
            // InternalVideoGen.g:2711:2: ( RULE_ID )
            // InternalVideoGen.g:2712:3: RULE_ID
            {
             before(grammarAccess.getAlternativesMediaAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAlternativesMediaAccess().getIdIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__IdAssignment_1"


    // $ANTLR start "rule__AlternativesMedia__MediasAssignment_3"
    // InternalVideoGen.g:2721:1: rule__AlternativesMedia__MediasAssignment_3 : ( ruleMediaDescription ) ;
    public final void rule__AlternativesMedia__MediasAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2725:1: ( ( ruleMediaDescription ) )
            // InternalVideoGen.g:2726:2: ( ruleMediaDescription )
            {
            // InternalVideoGen.g:2726:2: ( ruleMediaDescription )
            // InternalVideoGen.g:2727:3: ruleMediaDescription
            {
             before(grammarAccess.getAlternativesMediaAccess().getMediasMediaDescriptionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleMediaDescription();

            state._fsp--;

             after(grammarAccess.getAlternativesMediaAccess().getMediasMediaDescriptionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AlternativesMedia__MediasAssignment_3"


    // $ANTLR start "rule__ImageDescription__ImageidAssignment_1"
    // InternalVideoGen.g:2736:1: rule__ImageDescription__ImageidAssignment_1 : ( RULE_ID ) ;
    public final void rule__ImageDescription__ImageidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2740:1: ( ( RULE_ID ) )
            // InternalVideoGen.g:2741:2: ( RULE_ID )
            {
            // InternalVideoGen.g:2741:2: ( RULE_ID )
            // InternalVideoGen.g:2742:3: RULE_ID
            {
             before(grammarAccess.getImageDescriptionAccess().getImageidIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getImageidIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__ImageidAssignment_1"


    // $ANTLR start "rule__ImageDescription__LocationAssignment_2"
    // InternalVideoGen.g:2751:1: rule__ImageDescription__LocationAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ImageDescription__LocationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2755:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2756:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2756:2: ( RULE_STRING )
            // InternalVideoGen.g:2757:3: RULE_STRING
            {
             before(grammarAccess.getImageDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__LocationAssignment_2"


    // $ANTLR start "rule__ImageDescription__TopAssignment_3_1_1"
    // InternalVideoGen.g:2766:1: rule__ImageDescription__TopAssignment_3_1_1 : ( RULE_STRING ) ;
    public final void rule__ImageDescription__TopAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2770:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2771:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2771:2: ( RULE_STRING )
            // InternalVideoGen.g:2772:3: RULE_STRING
            {
             before(grammarAccess.getImageDescriptionAccess().getTopSTRINGTerminalRuleCall_3_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getTopSTRINGTerminalRuleCall_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__TopAssignment_3_1_1"


    // $ANTLR start "rule__ImageDescription__BottomAssignment_3_2_1"
    // InternalVideoGen.g:2781:1: rule__ImageDescription__BottomAssignment_3_2_1 : ( RULE_STRING ) ;
    public final void rule__ImageDescription__BottomAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2785:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2786:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2786:2: ( RULE_STRING )
            // InternalVideoGen.g:2787:3: RULE_STRING
            {
             before(grammarAccess.getImageDescriptionAccess().getBottomSTRINGTerminalRuleCall_3_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImageDescriptionAccess().getBottomSTRINGTerminalRuleCall_3_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImageDescription__BottomAssignment_3_2_1"


    // $ANTLR start "rule__VideoDescription__VideoidAssignment_1"
    // InternalVideoGen.g:2796:1: rule__VideoDescription__VideoidAssignment_1 : ( RULE_ID ) ;
    public final void rule__VideoDescription__VideoidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2800:1: ( ( RULE_ID ) )
            // InternalVideoGen.g:2801:2: ( RULE_ID )
            {
            // InternalVideoGen.g:2801:2: ( RULE_ID )
            // InternalVideoGen.g:2802:3: RULE_ID
            {
             before(grammarAccess.getVideoDescriptionAccess().getVideoidIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getVideoidIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__VideoidAssignment_1"


    // $ANTLR start "rule__VideoDescription__LocationAssignment_2"
    // InternalVideoGen.g:2811:1: rule__VideoDescription__LocationAssignment_2 : ( RULE_STRING ) ;
    public final void rule__VideoDescription__LocationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2815:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2816:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2816:2: ( RULE_STRING )
            // InternalVideoGen.g:2817:3: RULE_STRING
            {
             before(grammarAccess.getVideoDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__LocationAssignment_2"


    // $ANTLR start "rule__VideoDescription__DurationAssignment_3_1_1"
    // InternalVideoGen.g:2826:1: rule__VideoDescription__DurationAssignment_3_1_1 : ( RULE_INT ) ;
    public final void rule__VideoDescription__DurationAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2830:1: ( ( RULE_INT ) )
            // InternalVideoGen.g:2831:2: ( RULE_INT )
            {
            // InternalVideoGen.g:2831:2: ( RULE_INT )
            // InternalVideoGen.g:2832:3: RULE_INT
            {
             before(grammarAccess.getVideoDescriptionAccess().getDurationINTTerminalRuleCall_3_1_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getDurationINTTerminalRuleCall_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__DurationAssignment_3_1_1"


    // $ANTLR start "rule__VideoDescription__ProbabilityAssignment_3_2_1"
    // InternalVideoGen.g:2841:1: rule__VideoDescription__ProbabilityAssignment_3_2_1 : ( RULE_INT ) ;
    public final void rule__VideoDescription__ProbabilityAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2845:1: ( ( RULE_INT ) )
            // InternalVideoGen.g:2846:2: ( RULE_INT )
            {
            // InternalVideoGen.g:2846:2: ( RULE_INT )
            // InternalVideoGen.g:2847:3: RULE_INT
            {
             before(grammarAccess.getVideoDescriptionAccess().getProbabilityINTTerminalRuleCall_3_2_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getProbabilityINTTerminalRuleCall_3_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__ProbabilityAssignment_3_2_1"


    // $ANTLR start "rule__VideoDescription__DescriptionAssignment_3_3_1"
    // InternalVideoGen.g:2856:1: rule__VideoDescription__DescriptionAssignment_3_3_1 : ( RULE_STRING ) ;
    public final void rule__VideoDescription__DescriptionAssignment_3_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2860:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2861:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2861:2: ( RULE_STRING )
            // InternalVideoGen.g:2862:3: RULE_STRING
            {
             before(grammarAccess.getVideoDescriptionAccess().getDescriptionSTRINGTerminalRuleCall_3_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoDescriptionAccess().getDescriptionSTRINGTerminalRuleCall_3_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__DescriptionAssignment_3_3_1"


    // $ANTLR start "rule__VideoDescription__FilterAssignment_3_4_1"
    // InternalVideoGen.g:2871:1: rule__VideoDescription__FilterAssignment_3_4_1 : ( ruleFilter ) ;
    public final void rule__VideoDescription__FilterAssignment_3_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2875:1: ( ( ruleFilter ) )
            // InternalVideoGen.g:2876:2: ( ruleFilter )
            {
            // InternalVideoGen.g:2876:2: ( ruleFilter )
            // InternalVideoGen.g:2877:3: ruleFilter
            {
             before(grammarAccess.getVideoDescriptionAccess().getFilterFilterParserRuleCall_3_4_1_0()); 
            pushFollow(FOLLOW_2);
            ruleFilter();

            state._fsp--;

             after(grammarAccess.getVideoDescriptionAccess().getFilterFilterParserRuleCall_3_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__FilterAssignment_3_4_1"


    // $ANTLR start "rule__VideoDescription__TextAssignment_3_5_1"
    // InternalVideoGen.g:2886:1: rule__VideoDescription__TextAssignment_3_5_1 : ( ruleVideoText ) ;
    public final void rule__VideoDescription__TextAssignment_3_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2890:1: ( ( ruleVideoText ) )
            // InternalVideoGen.g:2891:2: ( ruleVideoText )
            {
            // InternalVideoGen.g:2891:2: ( ruleVideoText )
            // InternalVideoGen.g:2892:3: ruleVideoText
            {
             before(grammarAccess.getVideoDescriptionAccess().getTextVideoTextParserRuleCall_3_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVideoText();

            state._fsp--;

             after(grammarAccess.getVideoDescriptionAccess().getTextVideoTextParserRuleCall_3_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoDescription__TextAssignment_3_5_1"


    // $ANTLR start "rule__VideoText__ContentAssignment_2"
    // InternalVideoGen.g:2901:1: rule__VideoText__ContentAssignment_2 : ( RULE_STRING ) ;
    public final void rule__VideoText__ContentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2905:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2906:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2906:2: ( RULE_STRING )
            // InternalVideoGen.g:2907:3: RULE_STRING
            {
             before(grammarAccess.getVideoTextAccess().getContentSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getContentSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__ContentAssignment_2"


    // $ANTLR start "rule__VideoText__PositionAssignment_4"
    // InternalVideoGen.g:2916:1: rule__VideoText__PositionAssignment_4 : ( rulePosition ) ;
    public final void rule__VideoText__PositionAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2920:1: ( ( rulePosition ) )
            // InternalVideoGen.g:2921:2: ( rulePosition )
            {
            // InternalVideoGen.g:2921:2: ( rulePosition )
            // InternalVideoGen.g:2922:3: rulePosition
            {
             before(grammarAccess.getVideoTextAccess().getPositionPositionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            rulePosition();

            state._fsp--;

             after(grammarAccess.getVideoTextAccess().getPositionPositionParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__PositionAssignment_4"


    // $ANTLR start "rule__VideoText__ColorAssignment_5_1"
    // InternalVideoGen.g:2931:1: rule__VideoText__ColorAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__VideoText__ColorAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2935:1: ( ( RULE_STRING ) )
            // InternalVideoGen.g:2936:2: ( RULE_STRING )
            {
            // InternalVideoGen.g:2936:2: ( RULE_STRING )
            // InternalVideoGen.g:2937:3: RULE_STRING
            {
             before(grammarAccess.getVideoTextAccess().getColorSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getColorSTRINGTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__ColorAssignment_5_1"


    // $ANTLR start "rule__VideoText__SizeAssignment_6_1"
    // InternalVideoGen.g:2946:1: rule__VideoText__SizeAssignment_6_1 : ( RULE_INT ) ;
    public final void rule__VideoText__SizeAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2950:1: ( ( RULE_INT ) )
            // InternalVideoGen.g:2951:2: ( RULE_INT )
            {
            // InternalVideoGen.g:2951:2: ( RULE_INT )
            // InternalVideoGen.g:2952:3: RULE_INT
            {
             before(grammarAccess.getVideoTextAccess().getSizeINTTerminalRuleCall_6_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getVideoTextAccess().getSizeINTTerminalRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VideoText__SizeAssignment_6_1"


    // $ANTLR start "rule__FlipFilter__OrientationAssignment_1"
    // InternalVideoGen.g:2961:1: rule__FlipFilter__OrientationAssignment_1 : ( ( rule__FlipFilter__OrientationAlternatives_1_0 ) ) ;
    public final void rule__FlipFilter__OrientationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalVideoGen.g:2965:1: ( ( ( rule__FlipFilter__OrientationAlternatives_1_0 ) ) )
            // InternalVideoGen.g:2966:2: ( ( rule__FlipFilter__OrientationAlternatives_1_0 ) )
            {
            // InternalVideoGen.g:2966:2: ( ( rule__FlipFilter__OrientationAlternatives_1_0 ) )
            // InternalVideoGen.g:2967:3: ( rule__FlipFilter__OrientationAlternatives_1_0 )
            {
             before(grammarAccess.getFlipFilterAccess().getOrientationAlternatives_1_0()); 
            // InternalVideoGen.g:2968:3: ( rule__FlipFilter__OrientationAlternatives_1_0 )
            // InternalVideoGen.g:2968:4: rule__FlipFilter__OrientationAlternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__FlipFilter__OrientationAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getFlipFilterAccess().getOrientationAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlipFilter__OrientationAssignment_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000007000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000007000002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000048000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000048000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000F80000020L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000070000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x000000000000E000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000000C000000020L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00000000000F0000L});

}