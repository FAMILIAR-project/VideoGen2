package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.VideoGenGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalVideoGenParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_LEFT_BRACKET", "RULE_RIGHT_BRACKET", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'VideoGen'", "'@author'", "'@version'", "'@creation'", "'mandatory'", "'optional'", "'alternatives'", "'image'", "'toptext'", "'bottomtext'", "'videoseq'", "'duration'", "'probability'", "'description'", "'filter'", "'text'", "'content'", "'position'", "'color'", "'size'", "'TOP'", "'BOTTOM'", "'CENTER'", "'b&w'", "'negate'", "'flip'", "'h'", "'horizontal'", "'v'", "'vertical'"
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

        public InternalVideoGenParser(TokenStream input, VideoGenGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "VideoGeneratorModel";
       	}

       	@Override
       	protected VideoGenGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleVideoGeneratorModel"
    // InternalVideoGen.g:64:1: entryRuleVideoGeneratorModel returns [EObject current=null] : iv_ruleVideoGeneratorModel= ruleVideoGeneratorModel EOF ;
    public final EObject entryRuleVideoGeneratorModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVideoGeneratorModel = null;


        try {
            // InternalVideoGen.g:64:60: (iv_ruleVideoGeneratorModel= ruleVideoGeneratorModel EOF )
            // InternalVideoGen.g:65:2: iv_ruleVideoGeneratorModel= ruleVideoGeneratorModel EOF
            {
             newCompositeNode(grammarAccess.getVideoGeneratorModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVideoGeneratorModel=ruleVideoGeneratorModel();

            state._fsp--;

             current =iv_ruleVideoGeneratorModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVideoGeneratorModel"


    // $ANTLR start "ruleVideoGeneratorModel"
    // InternalVideoGen.g:71:1: ruleVideoGeneratorModel returns [EObject current=null] : ( ( (lv_information_0_0= ruleVideoGenInformation ) )? otherlv_1= 'VideoGen' this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMedia ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) ;
    public final EObject ruleVideoGeneratorModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_LEFT_BRACKET_2=null;
        Token this_RIGHT_BRACKET_4=null;
        EObject lv_information_0_0 = null;

        EObject lv_medias_3_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:77:2: ( ( ( (lv_information_0_0= ruleVideoGenInformation ) )? otherlv_1= 'VideoGen' this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMedia ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:78:2: ( ( (lv_information_0_0= ruleVideoGenInformation ) )? otherlv_1= 'VideoGen' this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMedia ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:78:2: ( ( (lv_information_0_0= ruleVideoGenInformation ) )? otherlv_1= 'VideoGen' this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMedia ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:79:3: ( (lv_information_0_0= ruleVideoGenInformation ) )? otherlv_1= 'VideoGen' this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMedia ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET
            {
            // InternalVideoGen.g:79:3: ( (lv_information_0_0= ruleVideoGenInformation ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==14) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalVideoGen.g:80:4: (lv_information_0_0= ruleVideoGenInformation )
                    {
                    // InternalVideoGen.g:80:4: (lv_information_0_0= ruleVideoGenInformation )
                    // InternalVideoGen.g:81:5: lv_information_0_0= ruleVideoGenInformation
                    {

                    					newCompositeNode(grammarAccess.getVideoGeneratorModelAccess().getInformationVideoGenInformationParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_3);
                    lv_information_0_0=ruleVideoGenInformation();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getVideoGeneratorModelRule());
                    					}
                    					set(
                    						current,
                    						"information",
                    						lv_information_0_0,
                    						"org.xtext.example.mydsl.VideoGen.VideoGenInformation");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getVideoGeneratorModelAccess().getVideoGenKeyword_1());
            		
            this_LEFT_BRACKET_2=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_5); 

            			newLeafNode(this_LEFT_BRACKET_2, grammarAccess.getVideoGeneratorModelAccess().getLEFT_BRACKETTerminalRuleCall_2());
            		
            // InternalVideoGen.g:106:3: ( (lv_medias_3_0= ruleMedia ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=17 && LA2_0<=19)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalVideoGen.g:107:4: (lv_medias_3_0= ruleMedia )
            	    {
            	    // InternalVideoGen.g:107:4: (lv_medias_3_0= ruleMedia )
            	    // InternalVideoGen.g:108:5: lv_medias_3_0= ruleMedia
            	    {

            	    					newCompositeNode(grammarAccess.getVideoGeneratorModelAccess().getMediasMediaParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_medias_3_0=ruleMedia();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getVideoGeneratorModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"medias",
            	    						lv_medias_3_0,
            	    						"org.xtext.example.mydsl.VideoGen.Media");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            this_RIGHT_BRACKET_4=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

            			newLeafNode(this_RIGHT_BRACKET_4, grammarAccess.getVideoGeneratorModelAccess().getRIGHT_BRACKETTerminalRuleCall_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVideoGeneratorModel"


    // $ANTLR start "entryRuleVideoGenInformation"
    // InternalVideoGen.g:133:1: entryRuleVideoGenInformation returns [EObject current=null] : iv_ruleVideoGenInformation= ruleVideoGenInformation EOF ;
    public final EObject entryRuleVideoGenInformation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVideoGenInformation = null;


        try {
            // InternalVideoGen.g:133:60: (iv_ruleVideoGenInformation= ruleVideoGenInformation EOF )
            // InternalVideoGen.g:134:2: iv_ruleVideoGenInformation= ruleVideoGenInformation EOF
            {
             newCompositeNode(grammarAccess.getVideoGenInformationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVideoGenInformation=ruleVideoGenInformation();

            state._fsp--;

             current =iv_ruleVideoGenInformation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVideoGenInformation"


    // $ANTLR start "ruleVideoGenInformation"
    // InternalVideoGen.g:140:1: ruleVideoGenInformation returns [EObject current=null] : ( () (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) ) (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )? (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleVideoGenInformation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_authorName_2_0=null;
        Token otherlv_3=null;
        Token lv_version_4_0=null;
        Token otherlv_5=null;
        Token lv_creationDate_6_0=null;


        	enterRule();

        try {
            // InternalVideoGen.g:146:2: ( ( () (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) ) (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )? (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )? ) )
            // InternalVideoGen.g:147:2: ( () (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) ) (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )? (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )? )
            {
            // InternalVideoGen.g:147:2: ( () (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) ) (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )? (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )? )
            // InternalVideoGen.g:148:3: () (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) ) (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )? (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )?
            {
            // InternalVideoGen.g:148:3: ()
            // InternalVideoGen.g:149:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVideoGenInformationAccess().getVideoGenInformationAction_0(),
            					current);
            			

            }

            // InternalVideoGen.g:155:3: (otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) ) )
            // InternalVideoGen.g:156:4: otherlv_1= '@author' ( (lv_authorName_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,14,FOLLOW_7); 

            				newLeafNode(otherlv_1, grammarAccess.getVideoGenInformationAccess().getAuthorKeyword_1_0());
            			
            // InternalVideoGen.g:160:4: ( (lv_authorName_2_0= RULE_STRING ) )
            // InternalVideoGen.g:161:5: (lv_authorName_2_0= RULE_STRING )
            {
            // InternalVideoGen.g:161:5: (lv_authorName_2_0= RULE_STRING )
            // InternalVideoGen.g:162:6: lv_authorName_2_0= RULE_STRING
            {
            lv_authorName_2_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            						newLeafNode(lv_authorName_2_0, grammarAccess.getVideoGenInformationAccess().getAuthorNameSTRINGTerminalRuleCall_1_1_0());
            					

            						if (current==null) {
            							current = createModelElement(grammarAccess.getVideoGenInformationRule());
            						}
            						setWithLastConsumed(
            							current,
            							"authorName",
            							lv_authorName_2_0,
            							"org.eclipse.xtext.common.Terminals.STRING");
            					

            }


            }


            }

            // InternalVideoGen.g:179:3: (otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalVideoGen.g:180:4: otherlv_3= '@version' ( (lv_version_4_0= RULE_STRING ) )
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_7); 

                    				newLeafNode(otherlv_3, grammarAccess.getVideoGenInformationAccess().getVersionKeyword_2_0());
                    			
                    // InternalVideoGen.g:184:4: ( (lv_version_4_0= RULE_STRING ) )
                    // InternalVideoGen.g:185:5: (lv_version_4_0= RULE_STRING )
                    {
                    // InternalVideoGen.g:185:5: (lv_version_4_0= RULE_STRING )
                    // InternalVideoGen.g:186:6: lv_version_4_0= RULE_STRING
                    {
                    lv_version_4_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

                    						newLeafNode(lv_version_4_0, grammarAccess.getVideoGenInformationAccess().getVersionSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVideoGenInformationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"version",
                    							lv_version_4_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalVideoGen.g:203:3: (otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalVideoGen.g:204:4: otherlv_5= '@creation' ( (lv_creationDate_6_0= RULE_STRING ) )
                    {
                    otherlv_5=(Token)match(input,16,FOLLOW_7); 

                    				newLeafNode(otherlv_5, grammarAccess.getVideoGenInformationAccess().getCreationKeyword_3_0());
                    			
                    // InternalVideoGen.g:208:4: ( (lv_creationDate_6_0= RULE_STRING ) )
                    // InternalVideoGen.g:209:5: (lv_creationDate_6_0= RULE_STRING )
                    {
                    // InternalVideoGen.g:209:5: (lv_creationDate_6_0= RULE_STRING )
                    // InternalVideoGen.g:210:6: lv_creationDate_6_0= RULE_STRING
                    {
                    lv_creationDate_6_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_creationDate_6_0, grammarAccess.getVideoGenInformationAccess().getCreationDateSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVideoGenInformationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"creationDate",
                    							lv_creationDate_6_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVideoGenInformation"


    // $ANTLR start "entryRuleMedia"
    // InternalVideoGen.g:231:1: entryRuleMedia returns [EObject current=null] : iv_ruleMedia= ruleMedia EOF ;
    public final EObject entryRuleMedia() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMedia = null;


        try {
            // InternalVideoGen.g:231:46: (iv_ruleMedia= ruleMedia EOF )
            // InternalVideoGen.g:232:2: iv_ruleMedia= ruleMedia EOF
            {
             newCompositeNode(grammarAccess.getMediaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMedia=ruleMedia();

            state._fsp--;

             current =iv_ruleMedia; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMedia"


    // $ANTLR start "ruleMedia"
    // InternalVideoGen.g:238:1: ruleMedia returns [EObject current=null] : (this_MandatoryMedia_0= ruleMandatoryMedia | this_OptionalMedia_1= ruleOptionalMedia | this_AlternativesMedia_2= ruleAlternativesMedia ) ;
    public final EObject ruleMedia() throws RecognitionException {
        EObject current = null;

        EObject this_MandatoryMedia_0 = null;

        EObject this_OptionalMedia_1 = null;

        EObject this_AlternativesMedia_2 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:244:2: ( (this_MandatoryMedia_0= ruleMandatoryMedia | this_OptionalMedia_1= ruleOptionalMedia | this_AlternativesMedia_2= ruleAlternativesMedia ) )
            // InternalVideoGen.g:245:2: (this_MandatoryMedia_0= ruleMandatoryMedia | this_OptionalMedia_1= ruleOptionalMedia | this_AlternativesMedia_2= ruleAlternativesMedia )
            {
            // InternalVideoGen.g:245:2: (this_MandatoryMedia_0= ruleMandatoryMedia | this_OptionalMedia_1= ruleOptionalMedia | this_AlternativesMedia_2= ruleAlternativesMedia )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt5=1;
                }
                break;
            case 18:
                {
                alt5=2;
                }
                break;
            case 19:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalVideoGen.g:246:3: this_MandatoryMedia_0= ruleMandatoryMedia
                    {

                    			newCompositeNode(grammarAccess.getMediaAccess().getMandatoryMediaParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_MandatoryMedia_0=ruleMandatoryMedia();

                    state._fsp--;


                    			current = this_MandatoryMedia_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:255:3: this_OptionalMedia_1= ruleOptionalMedia
                    {

                    			newCompositeNode(grammarAccess.getMediaAccess().getOptionalMediaParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_OptionalMedia_1=ruleOptionalMedia();

                    state._fsp--;


                    			current = this_OptionalMedia_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:264:3: this_AlternativesMedia_2= ruleAlternativesMedia
                    {

                    			newCompositeNode(grammarAccess.getMediaAccess().getAlternativesMediaParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_AlternativesMedia_2=ruleAlternativesMedia();

                    state._fsp--;


                    			current = this_AlternativesMedia_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMedia"


    // $ANTLR start "entryRuleMandatoryMedia"
    // InternalVideoGen.g:276:1: entryRuleMandatoryMedia returns [EObject current=null] : iv_ruleMandatoryMedia= ruleMandatoryMedia EOF ;
    public final EObject entryRuleMandatoryMedia() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryMedia = null;


        try {
            // InternalVideoGen.g:276:55: (iv_ruleMandatoryMedia= ruleMandatoryMedia EOF )
            // InternalVideoGen.g:277:2: iv_ruleMandatoryMedia= ruleMandatoryMedia EOF
            {
             newCompositeNode(grammarAccess.getMandatoryMediaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMandatoryMedia=ruleMandatoryMedia();

            state._fsp--;

             current =iv_ruleMandatoryMedia; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMandatoryMedia"


    // $ANTLR start "ruleMandatoryMedia"
    // InternalVideoGen.g:283:1: ruleMandatoryMedia returns [EObject current=null] : (otherlv_0= 'mandatory' ( (lv_description_1_0= ruleMediaDescription ) ) ) ;
    public final EObject ruleMandatoryMedia() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_description_1_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:289:2: ( (otherlv_0= 'mandatory' ( (lv_description_1_0= ruleMediaDescription ) ) ) )
            // InternalVideoGen.g:290:2: (otherlv_0= 'mandatory' ( (lv_description_1_0= ruleMediaDescription ) ) )
            {
            // InternalVideoGen.g:290:2: (otherlv_0= 'mandatory' ( (lv_description_1_0= ruleMediaDescription ) ) )
            // InternalVideoGen.g:291:3: otherlv_0= 'mandatory' ( (lv_description_1_0= ruleMediaDescription ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getMandatoryMediaAccess().getMandatoryKeyword_0());
            		
            // InternalVideoGen.g:295:3: ( (lv_description_1_0= ruleMediaDescription ) )
            // InternalVideoGen.g:296:4: (lv_description_1_0= ruleMediaDescription )
            {
            // InternalVideoGen.g:296:4: (lv_description_1_0= ruleMediaDescription )
            // InternalVideoGen.g:297:5: lv_description_1_0= ruleMediaDescription
            {

            					newCompositeNode(grammarAccess.getMandatoryMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_description_1_0=ruleMediaDescription();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMandatoryMediaRule());
            					}
            					set(
            						current,
            						"description",
            						lv_description_1_0,
            						"org.xtext.example.mydsl.VideoGen.MediaDescription");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMandatoryMedia"


    // $ANTLR start "entryRuleOptionalMedia"
    // InternalVideoGen.g:318:1: entryRuleOptionalMedia returns [EObject current=null] : iv_ruleOptionalMedia= ruleOptionalMedia EOF ;
    public final EObject entryRuleOptionalMedia() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalMedia = null;


        try {
            // InternalVideoGen.g:318:54: (iv_ruleOptionalMedia= ruleOptionalMedia EOF )
            // InternalVideoGen.g:319:2: iv_ruleOptionalMedia= ruleOptionalMedia EOF
            {
             newCompositeNode(grammarAccess.getOptionalMediaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionalMedia=ruleOptionalMedia();

            state._fsp--;

             current =iv_ruleOptionalMedia; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionalMedia"


    // $ANTLR start "ruleOptionalMedia"
    // InternalVideoGen.g:325:1: ruleOptionalMedia returns [EObject current=null] : (otherlv_0= 'optional' ( (lv_description_1_0= ruleMediaDescription ) ) ) ;
    public final EObject ruleOptionalMedia() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_description_1_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:331:2: ( (otherlv_0= 'optional' ( (lv_description_1_0= ruleMediaDescription ) ) ) )
            // InternalVideoGen.g:332:2: (otherlv_0= 'optional' ( (lv_description_1_0= ruleMediaDescription ) ) )
            {
            // InternalVideoGen.g:332:2: (otherlv_0= 'optional' ( (lv_description_1_0= ruleMediaDescription ) ) )
            // InternalVideoGen.g:333:3: otherlv_0= 'optional' ( (lv_description_1_0= ruleMediaDescription ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getOptionalMediaAccess().getOptionalKeyword_0());
            		
            // InternalVideoGen.g:337:3: ( (lv_description_1_0= ruleMediaDescription ) )
            // InternalVideoGen.g:338:4: (lv_description_1_0= ruleMediaDescription )
            {
            // InternalVideoGen.g:338:4: (lv_description_1_0= ruleMediaDescription )
            // InternalVideoGen.g:339:5: lv_description_1_0= ruleMediaDescription
            {

            					newCompositeNode(grammarAccess.getOptionalMediaAccess().getDescriptionMediaDescriptionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_description_1_0=ruleMediaDescription();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptionalMediaRule());
            					}
            					set(
            						current,
            						"description",
            						lv_description_1_0,
            						"org.xtext.example.mydsl.VideoGen.MediaDescription");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionalMedia"


    // $ANTLR start "entryRuleAlternativesMedia"
    // InternalVideoGen.g:360:1: entryRuleAlternativesMedia returns [EObject current=null] : iv_ruleAlternativesMedia= ruleAlternativesMedia EOF ;
    public final EObject entryRuleAlternativesMedia() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAlternativesMedia = null;


        try {
            // InternalVideoGen.g:360:58: (iv_ruleAlternativesMedia= ruleAlternativesMedia EOF )
            // InternalVideoGen.g:361:2: iv_ruleAlternativesMedia= ruleAlternativesMedia EOF
            {
             newCompositeNode(grammarAccess.getAlternativesMediaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAlternativesMedia=ruleAlternativesMedia();

            state._fsp--;

             current =iv_ruleAlternativesMedia; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAlternativesMedia"


    // $ANTLR start "ruleAlternativesMedia"
    // InternalVideoGen.g:367:1: ruleAlternativesMedia returns [EObject current=null] : (otherlv_0= 'alternatives' ( (lv_id_1_0= RULE_ID ) )? this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMediaDescription ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) ;
    public final EObject ruleAlternativesMedia() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token this_LEFT_BRACKET_2=null;
        Token this_RIGHT_BRACKET_4=null;
        EObject lv_medias_3_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:373:2: ( (otherlv_0= 'alternatives' ( (lv_id_1_0= RULE_ID ) )? this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMediaDescription ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:374:2: (otherlv_0= 'alternatives' ( (lv_id_1_0= RULE_ID ) )? this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMediaDescription ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:374:2: (otherlv_0= 'alternatives' ( (lv_id_1_0= RULE_ID ) )? this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMediaDescription ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:375:3: otherlv_0= 'alternatives' ( (lv_id_1_0= RULE_ID ) )? this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_medias_3_0= ruleMediaDescription ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET
            {
            otherlv_0=(Token)match(input,19,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getAlternativesMediaAccess().getAlternativesKeyword_0());
            		
            // InternalVideoGen.g:379:3: ( (lv_id_1_0= RULE_ID ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalVideoGen.g:380:4: (lv_id_1_0= RULE_ID )
                    {
                    // InternalVideoGen.g:380:4: (lv_id_1_0= RULE_ID )
                    // InternalVideoGen.g:381:5: lv_id_1_0= RULE_ID
                    {
                    lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

                    					newLeafNode(lv_id_1_0, grammarAccess.getAlternativesMediaAccess().getIdIDTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAlternativesMediaRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"id",
                    						lv_id_1_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }
                    break;

            }

            this_LEFT_BRACKET_2=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_10); 

            			newLeafNode(this_LEFT_BRACKET_2, grammarAccess.getAlternativesMediaAccess().getLEFT_BRACKETTerminalRuleCall_2());
            		
            // InternalVideoGen.g:401:3: ( (lv_medias_3_0= ruleMediaDescription ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==20||LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalVideoGen.g:402:4: (lv_medias_3_0= ruleMediaDescription )
            	    {
            	    // InternalVideoGen.g:402:4: (lv_medias_3_0= ruleMediaDescription )
            	    // InternalVideoGen.g:403:5: lv_medias_3_0= ruleMediaDescription
            	    {

            	    					newCompositeNode(grammarAccess.getAlternativesMediaAccess().getMediasMediaDescriptionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_medias_3_0=ruleMediaDescription();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAlternativesMediaRule());
            	    					}
            	    					add(
            	    						current,
            	    						"medias",
            	    						lv_medias_3_0,
            	    						"org.xtext.example.mydsl.VideoGen.MediaDescription");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            this_RIGHT_BRACKET_4=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

            			newLeafNode(this_RIGHT_BRACKET_4, grammarAccess.getAlternativesMediaAccess().getRIGHT_BRACKETTerminalRuleCall_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAlternativesMedia"


    // $ANTLR start "entryRuleMediaDescription"
    // InternalVideoGen.g:428:1: entryRuleMediaDescription returns [EObject current=null] : iv_ruleMediaDescription= ruleMediaDescription EOF ;
    public final EObject entryRuleMediaDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMediaDescription = null;


        try {
            // InternalVideoGen.g:428:57: (iv_ruleMediaDescription= ruleMediaDescription EOF )
            // InternalVideoGen.g:429:2: iv_ruleMediaDescription= ruleMediaDescription EOF
            {
             newCompositeNode(grammarAccess.getMediaDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMediaDescription=ruleMediaDescription();

            state._fsp--;

             current =iv_ruleMediaDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMediaDescription"


    // $ANTLR start "ruleMediaDescription"
    // InternalVideoGen.g:435:1: ruleMediaDescription returns [EObject current=null] : (this_ImageDescription_0= ruleImageDescription | this_VideoDescription_1= ruleVideoDescription ) ;
    public final EObject ruleMediaDescription() throws RecognitionException {
        EObject current = null;

        EObject this_ImageDescription_0 = null;

        EObject this_VideoDescription_1 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:441:2: ( (this_ImageDescription_0= ruleImageDescription | this_VideoDescription_1= ruleVideoDescription ) )
            // InternalVideoGen.g:442:2: (this_ImageDescription_0= ruleImageDescription | this_VideoDescription_1= ruleVideoDescription )
            {
            // InternalVideoGen.g:442:2: (this_ImageDescription_0= ruleImageDescription | this_VideoDescription_1= ruleVideoDescription )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            else if ( (LA8_0==23) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalVideoGen.g:443:3: this_ImageDescription_0= ruleImageDescription
                    {

                    			newCompositeNode(grammarAccess.getMediaDescriptionAccess().getImageDescriptionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ImageDescription_0=ruleImageDescription();

                    state._fsp--;


                    			current = this_ImageDescription_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:452:3: this_VideoDescription_1= ruleVideoDescription
                    {

                    			newCompositeNode(grammarAccess.getMediaDescriptionAccess().getVideoDescriptionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_VideoDescription_1=ruleVideoDescription();

                    state._fsp--;


                    			current = this_VideoDescription_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMediaDescription"


    // $ANTLR start "entryRuleImageDescription"
    // InternalVideoGen.g:464:1: entryRuleImageDescription returns [EObject current=null] : iv_ruleImageDescription= ruleImageDescription EOF ;
    public final EObject entryRuleImageDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImageDescription = null;


        try {
            // InternalVideoGen.g:464:57: (iv_ruleImageDescription= ruleImageDescription EOF )
            // InternalVideoGen.g:465:2: iv_ruleImageDescription= ruleImageDescription EOF
            {
             newCompositeNode(grammarAccess.getImageDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImageDescription=ruleImageDescription();

            state._fsp--;

             current =iv_ruleImageDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImageDescription"


    // $ANTLR start "ruleImageDescription"
    // InternalVideoGen.g:471:1: ruleImageDescription returns [EObject current=null] : (otherlv_0= 'image' ( (lv_imageid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )? ) ;
    public final EObject ruleImageDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_imageid_1_0=null;
        Token lv_location_2_0=null;
        Token this_LEFT_BRACKET_3=null;
        Token otherlv_4=null;
        Token lv_top_5_0=null;
        Token otherlv_6=null;
        Token lv_bottom_7_0=null;
        Token this_RIGHT_BRACKET_8=null;


        	enterRule();

        try {
            // InternalVideoGen.g:477:2: ( (otherlv_0= 'image' ( (lv_imageid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )? ) )
            // InternalVideoGen.g:478:2: (otherlv_0= 'image' ( (lv_imageid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )? )
            {
            // InternalVideoGen.g:478:2: (otherlv_0= 'image' ( (lv_imageid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )? )
            // InternalVideoGen.g:479:3: otherlv_0= 'image' ( (lv_imageid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )?
            {
            otherlv_0=(Token)match(input,20,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getImageDescriptionAccess().getImageKeyword_0());
            		
            // InternalVideoGen.g:483:3: ( (lv_imageid_1_0= RULE_ID ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalVideoGen.g:484:4: (lv_imageid_1_0= RULE_ID )
                    {
                    // InternalVideoGen.g:484:4: (lv_imageid_1_0= RULE_ID )
                    // InternalVideoGen.g:485:5: lv_imageid_1_0= RULE_ID
                    {
                    lv_imageid_1_0=(Token)match(input,RULE_ID,FOLLOW_7); 

                    					newLeafNode(lv_imageid_1_0, grammarAccess.getImageDescriptionAccess().getImageidIDTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getImageDescriptionRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"imageid",
                    						lv_imageid_1_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }
                    break;

            }

            // InternalVideoGen.g:501:3: ( (lv_location_2_0= RULE_STRING ) )
            // InternalVideoGen.g:502:4: (lv_location_2_0= RULE_STRING )
            {
            // InternalVideoGen.g:502:4: (lv_location_2_0= RULE_STRING )
            // InternalVideoGen.g:503:5: lv_location_2_0= RULE_STRING
            {
            lv_location_2_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            					newLeafNode(lv_location_2_0, grammarAccess.getImageDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getImageDescriptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalVideoGen.g:519:3: (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_LEFT_BRACKET) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalVideoGen.g:520:4: this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) ) (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) ) this_RIGHT_BRACKET_8= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_3=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_15); 

                    				newLeafNode(this_LEFT_BRACKET_3, grammarAccess.getImageDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0());
                    			
                    // InternalVideoGen.g:524:4: (otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) ) )
                    // InternalVideoGen.g:525:5: otherlv_4= 'toptext' ( (lv_top_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,21,FOLLOW_7); 

                    					newLeafNode(otherlv_4, grammarAccess.getImageDescriptionAccess().getToptextKeyword_3_1_0());
                    				
                    // InternalVideoGen.g:529:5: ( (lv_top_5_0= RULE_STRING ) )
                    // InternalVideoGen.g:530:6: (lv_top_5_0= RULE_STRING )
                    {
                    // InternalVideoGen.g:530:6: (lv_top_5_0= RULE_STRING )
                    // InternalVideoGen.g:531:7: lv_top_5_0= RULE_STRING
                    {
                    lv_top_5_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

                    							newLeafNode(lv_top_5_0, grammarAccess.getImageDescriptionAccess().getTopSTRINGTerminalRuleCall_3_1_1_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getImageDescriptionRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"top",
                    								lv_top_5_0,
                    								"org.eclipse.xtext.common.Terminals.STRING");
                    						

                    }


                    }


                    }

                    // InternalVideoGen.g:548:4: (otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) ) )
                    // InternalVideoGen.g:549:5: otherlv_6= 'bottomtext' ( (lv_bottom_7_0= RULE_STRING ) )
                    {
                    otherlv_6=(Token)match(input,22,FOLLOW_7); 

                    					newLeafNode(otherlv_6, grammarAccess.getImageDescriptionAccess().getBottomtextKeyword_3_2_0());
                    				
                    // InternalVideoGen.g:553:5: ( (lv_bottom_7_0= RULE_STRING ) )
                    // InternalVideoGen.g:554:6: (lv_bottom_7_0= RULE_STRING )
                    {
                    // InternalVideoGen.g:554:6: (lv_bottom_7_0= RULE_STRING )
                    // InternalVideoGen.g:555:7: lv_bottom_7_0= RULE_STRING
                    {
                    lv_bottom_7_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

                    							newLeafNode(lv_bottom_7_0, grammarAccess.getImageDescriptionAccess().getBottomSTRINGTerminalRuleCall_3_2_1_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getImageDescriptionRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"bottom",
                    								lv_bottom_7_0,
                    								"org.eclipse.xtext.common.Terminals.STRING");
                    						

                    }


                    }


                    }

                    this_RIGHT_BRACKET_8=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

                    				newLeafNode(this_RIGHT_BRACKET_8, grammarAccess.getImageDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImageDescription"


    // $ANTLR start "entryRuleVideoDescription"
    // InternalVideoGen.g:581:1: entryRuleVideoDescription returns [EObject current=null] : iv_ruleVideoDescription= ruleVideoDescription EOF ;
    public final EObject entryRuleVideoDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVideoDescription = null;


        try {
            // InternalVideoGen.g:581:57: (iv_ruleVideoDescription= ruleVideoDescription EOF )
            // InternalVideoGen.g:582:2: iv_ruleVideoDescription= ruleVideoDescription EOF
            {
             newCompositeNode(grammarAccess.getVideoDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVideoDescription=ruleVideoDescription();

            state._fsp--;

             current =iv_ruleVideoDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVideoDescription"


    // $ANTLR start "ruleVideoDescription"
    // InternalVideoGen.g:588:1: ruleVideoDescription returns [EObject current=null] : (otherlv_0= 'videoseq' ( (lv_videoid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )? ) ;
    public final EObject ruleVideoDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_videoid_1_0=null;
        Token lv_location_2_0=null;
        Token this_LEFT_BRACKET_3=null;
        Token otherlv_4=null;
        Token lv_duration_5_0=null;
        Token otherlv_6=null;
        Token lv_probability_7_0=null;
        Token otherlv_8=null;
        Token lv_description_9_0=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token this_RIGHT_BRACKET_14=null;
        EObject lv_filter_11_0 = null;

        EObject lv_text_13_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:594:2: ( (otherlv_0= 'videoseq' ( (lv_videoid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )? ) )
            // InternalVideoGen.g:595:2: (otherlv_0= 'videoseq' ( (lv_videoid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )? )
            {
            // InternalVideoGen.g:595:2: (otherlv_0= 'videoseq' ( (lv_videoid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )? )
            // InternalVideoGen.g:596:3: otherlv_0= 'videoseq' ( (lv_videoid_1_0= RULE_ID ) )? ( (lv_location_2_0= RULE_STRING ) ) (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )?
            {
            otherlv_0=(Token)match(input,23,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getVideoDescriptionAccess().getVideoseqKeyword_0());
            		
            // InternalVideoGen.g:600:3: ( (lv_videoid_1_0= RULE_ID ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalVideoGen.g:601:4: (lv_videoid_1_0= RULE_ID )
                    {
                    // InternalVideoGen.g:601:4: (lv_videoid_1_0= RULE_ID )
                    // InternalVideoGen.g:602:5: lv_videoid_1_0= RULE_ID
                    {
                    lv_videoid_1_0=(Token)match(input,RULE_ID,FOLLOW_7); 

                    					newLeafNode(lv_videoid_1_0, grammarAccess.getVideoDescriptionAccess().getVideoidIDTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getVideoDescriptionRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"videoid",
                    						lv_videoid_1_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }
                    break;

            }

            // InternalVideoGen.g:618:3: ( (lv_location_2_0= RULE_STRING ) )
            // InternalVideoGen.g:619:4: (lv_location_2_0= RULE_STRING )
            {
            // InternalVideoGen.g:619:4: (lv_location_2_0= RULE_STRING )
            // InternalVideoGen.g:620:5: lv_location_2_0= RULE_STRING
            {
            lv_location_2_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            					newLeafNode(lv_location_2_0, grammarAccess.getVideoDescriptionAccess().getLocationSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVideoDescriptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalVideoGen.g:636:3: (this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_LEFT_BRACKET) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalVideoGen.g:637:4: this_LEFT_BRACKET_3= RULE_LEFT_BRACKET (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )? (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )? (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )? (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )? this_RIGHT_BRACKET_14= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_3=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_18); 

                    				newLeafNode(this_LEFT_BRACKET_3, grammarAccess.getVideoDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0());
                    			
                    // InternalVideoGen.g:641:4: (otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) ) )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==24) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // InternalVideoGen.g:642:5: otherlv_4= 'duration' ( (lv_duration_5_0= RULE_INT ) )
                            {
                            otherlv_4=(Token)match(input,24,FOLLOW_19); 

                            					newLeafNode(otherlv_4, grammarAccess.getVideoDescriptionAccess().getDurationKeyword_3_1_0());
                            				
                            // InternalVideoGen.g:646:5: ( (lv_duration_5_0= RULE_INT ) )
                            // InternalVideoGen.g:647:6: (lv_duration_5_0= RULE_INT )
                            {
                            // InternalVideoGen.g:647:6: (lv_duration_5_0= RULE_INT )
                            // InternalVideoGen.g:648:7: lv_duration_5_0= RULE_INT
                            {
                            lv_duration_5_0=(Token)match(input,RULE_INT,FOLLOW_20); 

                            							newLeafNode(lv_duration_5_0, grammarAccess.getVideoDescriptionAccess().getDurationINTTerminalRuleCall_3_1_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getVideoDescriptionRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"duration",
                            								lv_duration_5_0,
                            								"org.eclipse.xtext.common.Terminals.INT");
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalVideoGen.g:665:4: (otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) ) )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==25) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // InternalVideoGen.g:666:5: otherlv_6= 'probability' ( (lv_probability_7_0= RULE_INT ) )
                            {
                            otherlv_6=(Token)match(input,25,FOLLOW_19); 

                            					newLeafNode(otherlv_6, grammarAccess.getVideoDescriptionAccess().getProbabilityKeyword_3_2_0());
                            				
                            // InternalVideoGen.g:670:5: ( (lv_probability_7_0= RULE_INT ) )
                            // InternalVideoGen.g:671:6: (lv_probability_7_0= RULE_INT )
                            {
                            // InternalVideoGen.g:671:6: (lv_probability_7_0= RULE_INT )
                            // InternalVideoGen.g:672:7: lv_probability_7_0= RULE_INT
                            {
                            lv_probability_7_0=(Token)match(input,RULE_INT,FOLLOW_21); 

                            							newLeafNode(lv_probability_7_0, grammarAccess.getVideoDescriptionAccess().getProbabilityINTTerminalRuleCall_3_2_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getVideoDescriptionRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"probability",
                            								lv_probability_7_0,
                            								"org.eclipse.xtext.common.Terminals.INT");
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalVideoGen.g:689:4: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==26) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalVideoGen.g:690:5: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                            {
                            otherlv_8=(Token)match(input,26,FOLLOW_7); 

                            					newLeafNode(otherlv_8, grammarAccess.getVideoDescriptionAccess().getDescriptionKeyword_3_3_0());
                            				
                            // InternalVideoGen.g:694:5: ( (lv_description_9_0= RULE_STRING ) )
                            // InternalVideoGen.g:695:6: (lv_description_9_0= RULE_STRING )
                            {
                            // InternalVideoGen.g:695:6: (lv_description_9_0= RULE_STRING )
                            // InternalVideoGen.g:696:7: lv_description_9_0= RULE_STRING
                            {
                            lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

                            							newLeafNode(lv_description_9_0, grammarAccess.getVideoDescriptionAccess().getDescriptionSTRINGTerminalRuleCall_3_3_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getVideoDescriptionRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"description",
                            								lv_description_9_0,
                            								"org.eclipse.xtext.common.Terminals.STRING");
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalVideoGen.g:713:4: (otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) ) )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==27) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalVideoGen.g:714:5: otherlv_10= 'filter' ( (lv_filter_11_0= ruleFilter ) )
                            {
                            otherlv_10=(Token)match(input,27,FOLLOW_23); 

                            					newLeafNode(otherlv_10, grammarAccess.getVideoDescriptionAccess().getFilterKeyword_3_4_0());
                            				
                            // InternalVideoGen.g:718:5: ( (lv_filter_11_0= ruleFilter ) )
                            // InternalVideoGen.g:719:6: (lv_filter_11_0= ruleFilter )
                            {
                            // InternalVideoGen.g:719:6: (lv_filter_11_0= ruleFilter )
                            // InternalVideoGen.g:720:7: lv_filter_11_0= ruleFilter
                            {

                            							newCompositeNode(grammarAccess.getVideoDescriptionAccess().getFilterFilterParserRuleCall_3_4_1_0());
                            						
                            pushFollow(FOLLOW_24);
                            lv_filter_11_0=ruleFilter();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getVideoDescriptionRule());
                            							}
                            							set(
                            								current,
                            								"filter",
                            								lv_filter_11_0,
                            								"org.xtext.example.mydsl.VideoGen.Filter");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalVideoGen.g:738:4: (otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==28) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalVideoGen.g:739:5: otherlv_12= 'text' ( (lv_text_13_0= ruleVideoText ) )
                            {
                            otherlv_12=(Token)match(input,28,FOLLOW_4); 

                            					newLeafNode(otherlv_12, grammarAccess.getVideoDescriptionAccess().getTextKeyword_3_5_0());
                            				
                            // InternalVideoGen.g:743:5: ( (lv_text_13_0= ruleVideoText ) )
                            // InternalVideoGen.g:744:6: (lv_text_13_0= ruleVideoText )
                            {
                            // InternalVideoGen.g:744:6: (lv_text_13_0= ruleVideoText )
                            // InternalVideoGen.g:745:7: lv_text_13_0= ruleVideoText
                            {

                            							newCompositeNode(grammarAccess.getVideoDescriptionAccess().getTextVideoTextParserRuleCall_3_5_1_0());
                            						
                            pushFollow(FOLLOW_17);
                            lv_text_13_0=ruleVideoText();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getVideoDescriptionRule());
                            							}
                            							set(
                            								current,
                            								"text",
                            								lv_text_13_0,
                            								"org.xtext.example.mydsl.VideoGen.VideoText");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    this_RIGHT_BRACKET_14=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

                    				newLeafNode(this_RIGHT_BRACKET_14, grammarAccess.getVideoDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_6());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVideoDescription"


    // $ANTLR start "entryRuleVideoText"
    // InternalVideoGen.g:772:1: entryRuleVideoText returns [EObject current=null] : iv_ruleVideoText= ruleVideoText EOF ;
    public final EObject entryRuleVideoText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVideoText = null;


        try {
            // InternalVideoGen.g:772:50: (iv_ruleVideoText= ruleVideoText EOF )
            // InternalVideoGen.g:773:2: iv_ruleVideoText= ruleVideoText EOF
            {
             newCompositeNode(grammarAccess.getVideoTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVideoText=ruleVideoText();

            state._fsp--;

             current =iv_ruleVideoText; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVideoText"


    // $ANTLR start "ruleVideoText"
    // InternalVideoGen.g:779:1: ruleVideoText returns [EObject current=null] : (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET otherlv_1= 'content' ( (lv_content_2_0= RULE_STRING ) ) otherlv_3= 'position' ( (lv_position_4_0= rulePosition ) ) (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )? (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )? this_RIGHT_BRACKET_9= RULE_RIGHT_BRACKET ) ;
    public final EObject ruleVideoText() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_BRACKET_0=null;
        Token otherlv_1=null;
        Token lv_content_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token lv_color_6_0=null;
        Token otherlv_7=null;
        Token lv_size_8_0=null;
        Token this_RIGHT_BRACKET_9=null;
        AntlrDatatypeRuleToken lv_position_4_0 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:785:2: ( (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET otherlv_1= 'content' ( (lv_content_2_0= RULE_STRING ) ) otherlv_3= 'position' ( (lv_position_4_0= rulePosition ) ) (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )? (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )? this_RIGHT_BRACKET_9= RULE_RIGHT_BRACKET ) )
            // InternalVideoGen.g:786:2: (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET otherlv_1= 'content' ( (lv_content_2_0= RULE_STRING ) ) otherlv_3= 'position' ( (lv_position_4_0= rulePosition ) ) (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )? (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )? this_RIGHT_BRACKET_9= RULE_RIGHT_BRACKET )
            {
            // InternalVideoGen.g:786:2: (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET otherlv_1= 'content' ( (lv_content_2_0= RULE_STRING ) ) otherlv_3= 'position' ( (lv_position_4_0= rulePosition ) ) (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )? (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )? this_RIGHT_BRACKET_9= RULE_RIGHT_BRACKET )
            // InternalVideoGen.g:787:3: this_LEFT_BRACKET_0= RULE_LEFT_BRACKET otherlv_1= 'content' ( (lv_content_2_0= RULE_STRING ) ) otherlv_3= 'position' ( (lv_position_4_0= rulePosition ) ) (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )? (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )? this_RIGHT_BRACKET_9= RULE_RIGHT_BRACKET
            {
            this_LEFT_BRACKET_0=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_25); 

            			newLeafNode(this_LEFT_BRACKET_0, grammarAccess.getVideoTextAccess().getLEFT_BRACKETTerminalRuleCall_0());
            		
            otherlv_1=(Token)match(input,29,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getVideoTextAccess().getContentKeyword_1());
            		
            // InternalVideoGen.g:795:3: ( (lv_content_2_0= RULE_STRING ) )
            // InternalVideoGen.g:796:4: (lv_content_2_0= RULE_STRING )
            {
            // InternalVideoGen.g:796:4: (lv_content_2_0= RULE_STRING )
            // InternalVideoGen.g:797:5: lv_content_2_0= RULE_STRING
            {
            lv_content_2_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_content_2_0, grammarAccess.getVideoTextAccess().getContentSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVideoTextRule());
            					}
            					setWithLastConsumed(
            						current,
            						"content",
            						lv_content_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,30,FOLLOW_27); 

            			newLeafNode(otherlv_3, grammarAccess.getVideoTextAccess().getPositionKeyword_3());
            		
            // InternalVideoGen.g:817:3: ( (lv_position_4_0= rulePosition ) )
            // InternalVideoGen.g:818:4: (lv_position_4_0= rulePosition )
            {
            // InternalVideoGen.g:818:4: (lv_position_4_0= rulePosition )
            // InternalVideoGen.g:819:5: lv_position_4_0= rulePosition
            {

            					newCompositeNode(grammarAccess.getVideoTextAccess().getPositionPositionParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_28);
            lv_position_4_0=rulePosition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVideoTextRule());
            					}
            					set(
            						current,
            						"position",
            						lv_position_4_0,
            						"org.xtext.example.mydsl.VideoGen.Position");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalVideoGen.g:836:3: (otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalVideoGen.g:837:4: otherlv_5= 'color' ( (lv_color_6_0= RULE_STRING ) )
                    {
                    otherlv_5=(Token)match(input,31,FOLLOW_7); 

                    				newLeafNode(otherlv_5, grammarAccess.getVideoTextAccess().getColorKeyword_5_0());
                    			
                    // InternalVideoGen.g:841:4: ( (lv_color_6_0= RULE_STRING ) )
                    // InternalVideoGen.g:842:5: (lv_color_6_0= RULE_STRING )
                    {
                    // InternalVideoGen.g:842:5: (lv_color_6_0= RULE_STRING )
                    // InternalVideoGen.g:843:6: lv_color_6_0= RULE_STRING
                    {
                    lv_color_6_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

                    						newLeafNode(lv_color_6_0, grammarAccess.getVideoTextAccess().getColorSTRINGTerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVideoTextRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"color",
                    							lv_color_6_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalVideoGen.g:860:3: (otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==32) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalVideoGen.g:861:4: otherlv_7= 'size' ( (lv_size_8_0= RULE_INT ) )
                    {
                    otherlv_7=(Token)match(input,32,FOLLOW_19); 

                    				newLeafNode(otherlv_7, grammarAccess.getVideoTextAccess().getSizeKeyword_6_0());
                    			
                    // InternalVideoGen.g:865:4: ( (lv_size_8_0= RULE_INT ) )
                    // InternalVideoGen.g:866:5: (lv_size_8_0= RULE_INT )
                    {
                    // InternalVideoGen.g:866:5: (lv_size_8_0= RULE_INT )
                    // InternalVideoGen.g:867:6: lv_size_8_0= RULE_INT
                    {
                    lv_size_8_0=(Token)match(input,RULE_INT,FOLLOW_17); 

                    						newLeafNode(lv_size_8_0, grammarAccess.getVideoTextAccess().getSizeINTTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVideoTextRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"size",
                    							lv_size_8_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            this_RIGHT_BRACKET_9=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

            			newLeafNode(this_RIGHT_BRACKET_9, grammarAccess.getVideoTextAccess().getRIGHT_BRACKETTerminalRuleCall_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVideoText"


    // $ANTLR start "entryRulePosition"
    // InternalVideoGen.g:892:1: entryRulePosition returns [String current=null] : iv_rulePosition= rulePosition EOF ;
    public final String entryRulePosition() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePosition = null;


        try {
            // InternalVideoGen.g:892:48: (iv_rulePosition= rulePosition EOF )
            // InternalVideoGen.g:893:2: iv_rulePosition= rulePosition EOF
            {
             newCompositeNode(grammarAccess.getPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePosition=rulePosition();

            state._fsp--;

             current =iv_rulePosition.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePosition"


    // $ANTLR start "rulePosition"
    // InternalVideoGen.g:899:1: rulePosition returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'TOP' | kw= 'BOTTOM' | kw= 'CENTER' ) ;
    public final AntlrDatatypeRuleToken rulePosition() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalVideoGen.g:905:2: ( (kw= 'TOP' | kw= 'BOTTOM' | kw= 'CENTER' ) )
            // InternalVideoGen.g:906:2: (kw= 'TOP' | kw= 'BOTTOM' | kw= 'CENTER' )
            {
            // InternalVideoGen.g:906:2: (kw= 'TOP' | kw= 'BOTTOM' | kw= 'CENTER' )
            int alt20=3;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt20=1;
                }
                break;
            case 34:
                {
                alt20=2;
                }
                break;
            case 35:
                {
                alt20=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // InternalVideoGen.g:907:3: kw= 'TOP'
                    {
                    kw=(Token)match(input,33,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAccess().getTOPKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:913:3: kw= 'BOTTOM'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAccess().getBOTTOMKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:919:3: kw= 'CENTER'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAccess().getCENTERKeyword_2());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePosition"


    // $ANTLR start "entryRuleFilter"
    // InternalVideoGen.g:928:1: entryRuleFilter returns [EObject current=null] : iv_ruleFilter= ruleFilter EOF ;
    public final EObject entryRuleFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFilter = null;


        try {
            // InternalVideoGen.g:928:47: (iv_ruleFilter= ruleFilter EOF )
            // InternalVideoGen.g:929:2: iv_ruleFilter= ruleFilter EOF
            {
             newCompositeNode(grammarAccess.getFilterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFilter=ruleFilter();

            state._fsp--;

             current =iv_ruleFilter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFilter"


    // $ANTLR start "ruleFilter"
    // InternalVideoGen.g:935:1: ruleFilter returns [EObject current=null] : (this_FlipFilter_0= ruleFlipFilter | this_NegateFilter_1= ruleNegateFilter | this_BlackWhiteFilter_2= ruleBlackWhiteFilter ) ;
    public final EObject ruleFilter() throws RecognitionException {
        EObject current = null;

        EObject this_FlipFilter_0 = null;

        EObject this_NegateFilter_1 = null;

        EObject this_BlackWhiteFilter_2 = null;



        	enterRule();

        try {
            // InternalVideoGen.g:941:2: ( (this_FlipFilter_0= ruleFlipFilter | this_NegateFilter_1= ruleNegateFilter | this_BlackWhiteFilter_2= ruleBlackWhiteFilter ) )
            // InternalVideoGen.g:942:2: (this_FlipFilter_0= ruleFlipFilter | this_NegateFilter_1= ruleNegateFilter | this_BlackWhiteFilter_2= ruleBlackWhiteFilter )
            {
            // InternalVideoGen.g:942:2: (this_FlipFilter_0= ruleFlipFilter | this_NegateFilter_1= ruleNegateFilter | this_BlackWhiteFilter_2= ruleBlackWhiteFilter )
            int alt21=3;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt21=1;
                }
                break;
            case 37:
                {
                alt21=2;
                }
                break;
            case 36:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // InternalVideoGen.g:943:3: this_FlipFilter_0= ruleFlipFilter
                    {

                    			newCompositeNode(grammarAccess.getFilterAccess().getFlipFilterParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_FlipFilter_0=ruleFlipFilter();

                    state._fsp--;


                    			current = this_FlipFilter_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:952:3: this_NegateFilter_1= ruleNegateFilter
                    {

                    			newCompositeNode(grammarAccess.getFilterAccess().getNegateFilterParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_NegateFilter_1=ruleNegateFilter();

                    state._fsp--;


                    			current = this_NegateFilter_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:961:3: this_BlackWhiteFilter_2= ruleBlackWhiteFilter
                    {

                    			newCompositeNode(grammarAccess.getFilterAccess().getBlackWhiteFilterParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_BlackWhiteFilter_2=ruleBlackWhiteFilter();

                    state._fsp--;


                    			current = this_BlackWhiteFilter_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFilter"


    // $ANTLR start "entryRuleBlackWhiteFilter"
    // InternalVideoGen.g:973:1: entryRuleBlackWhiteFilter returns [EObject current=null] : iv_ruleBlackWhiteFilter= ruleBlackWhiteFilter EOF ;
    public final EObject entryRuleBlackWhiteFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlackWhiteFilter = null;


        try {
            // InternalVideoGen.g:973:57: (iv_ruleBlackWhiteFilter= ruleBlackWhiteFilter EOF )
            // InternalVideoGen.g:974:2: iv_ruleBlackWhiteFilter= ruleBlackWhiteFilter EOF
            {
             newCompositeNode(grammarAccess.getBlackWhiteFilterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBlackWhiteFilter=ruleBlackWhiteFilter();

            state._fsp--;

             current =iv_ruleBlackWhiteFilter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlackWhiteFilter"


    // $ANTLR start "ruleBlackWhiteFilter"
    // InternalVideoGen.g:980:1: ruleBlackWhiteFilter returns [EObject current=null] : ( () otherlv_1= 'b&w' ) ;
    public final EObject ruleBlackWhiteFilter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalVideoGen.g:986:2: ( ( () otherlv_1= 'b&w' ) )
            // InternalVideoGen.g:987:2: ( () otherlv_1= 'b&w' )
            {
            // InternalVideoGen.g:987:2: ( () otherlv_1= 'b&w' )
            // InternalVideoGen.g:988:3: () otherlv_1= 'b&w'
            {
            // InternalVideoGen.g:988:3: ()
            // InternalVideoGen.g:989:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBlackWhiteFilterAccess().getBlackWhiteFilterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getBlackWhiteFilterAccess().getBWKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlackWhiteFilter"


    // $ANTLR start "entryRuleNegateFilter"
    // InternalVideoGen.g:1003:1: entryRuleNegateFilter returns [EObject current=null] : iv_ruleNegateFilter= ruleNegateFilter EOF ;
    public final EObject entryRuleNegateFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNegateFilter = null;


        try {
            // InternalVideoGen.g:1003:53: (iv_ruleNegateFilter= ruleNegateFilter EOF )
            // InternalVideoGen.g:1004:2: iv_ruleNegateFilter= ruleNegateFilter EOF
            {
             newCompositeNode(grammarAccess.getNegateFilterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNegateFilter=ruleNegateFilter();

            state._fsp--;

             current =iv_ruleNegateFilter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegateFilter"


    // $ANTLR start "ruleNegateFilter"
    // InternalVideoGen.g:1010:1: ruleNegateFilter returns [EObject current=null] : ( () otherlv_1= 'negate' ) ;
    public final EObject ruleNegateFilter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalVideoGen.g:1016:2: ( ( () otherlv_1= 'negate' ) )
            // InternalVideoGen.g:1017:2: ( () otherlv_1= 'negate' )
            {
            // InternalVideoGen.g:1017:2: ( () otherlv_1= 'negate' )
            // InternalVideoGen.g:1018:3: () otherlv_1= 'negate'
            {
            // InternalVideoGen.g:1018:3: ()
            // InternalVideoGen.g:1019:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNegateFilterAccess().getNegateFilterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getNegateFilterAccess().getNegateKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegateFilter"


    // $ANTLR start "entryRuleFlipFilter"
    // InternalVideoGen.g:1033:1: entryRuleFlipFilter returns [EObject current=null] : iv_ruleFlipFilter= ruleFlipFilter EOF ;
    public final EObject entryRuleFlipFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFlipFilter = null;


        try {
            // InternalVideoGen.g:1033:51: (iv_ruleFlipFilter= ruleFlipFilter EOF )
            // InternalVideoGen.g:1034:2: iv_ruleFlipFilter= ruleFlipFilter EOF
            {
             newCompositeNode(grammarAccess.getFlipFilterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFlipFilter=ruleFlipFilter();

            state._fsp--;

             current =iv_ruleFlipFilter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFlipFilter"


    // $ANTLR start "ruleFlipFilter"
    // InternalVideoGen.g:1040:1: ruleFlipFilter returns [EObject current=null] : (otherlv_0= 'flip' ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) ) ) ;
    public final EObject ruleFlipFilter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_orientation_1_1=null;
        Token lv_orientation_1_2=null;
        Token lv_orientation_1_3=null;
        Token lv_orientation_1_4=null;


        	enterRule();

        try {
            // InternalVideoGen.g:1046:2: ( (otherlv_0= 'flip' ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) ) ) )
            // InternalVideoGen.g:1047:2: (otherlv_0= 'flip' ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) ) )
            {
            // InternalVideoGen.g:1047:2: (otherlv_0= 'flip' ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) ) )
            // InternalVideoGen.g:1048:3: otherlv_0= 'flip' ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) )
            {
            otherlv_0=(Token)match(input,38,FOLLOW_30); 

            			newLeafNode(otherlv_0, grammarAccess.getFlipFilterAccess().getFlipKeyword_0());
            		
            // InternalVideoGen.g:1052:3: ( ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) ) )
            // InternalVideoGen.g:1053:4: ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) )
            {
            // InternalVideoGen.g:1053:4: ( (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' ) )
            // InternalVideoGen.g:1054:5: (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' )
            {
            // InternalVideoGen.g:1054:5: (lv_orientation_1_1= 'h' | lv_orientation_1_2= 'horizontal' | lv_orientation_1_3= 'v' | lv_orientation_1_4= 'vertical' )
            int alt22=4;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt22=1;
                }
                break;
            case 40:
                {
                alt22=2;
                }
                break;
            case 41:
                {
                alt22=3;
                }
                break;
            case 42:
                {
                alt22=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // InternalVideoGen.g:1055:6: lv_orientation_1_1= 'h'
                    {
                    lv_orientation_1_1=(Token)match(input,39,FOLLOW_2); 

                    						newLeafNode(lv_orientation_1_1, grammarAccess.getFlipFilterAccess().getOrientationHKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFlipFilterRule());
                    						}
                    						setWithLastConsumed(current, "orientation", lv_orientation_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalVideoGen.g:1066:6: lv_orientation_1_2= 'horizontal'
                    {
                    lv_orientation_1_2=(Token)match(input,40,FOLLOW_2); 

                    						newLeafNode(lv_orientation_1_2, grammarAccess.getFlipFilterAccess().getOrientationHorizontalKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFlipFilterRule());
                    						}
                    						setWithLastConsumed(current, "orientation", lv_orientation_1_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalVideoGen.g:1077:6: lv_orientation_1_3= 'v'
                    {
                    lv_orientation_1_3=(Token)match(input,41,FOLLOW_2); 

                    						newLeafNode(lv_orientation_1_3, grammarAccess.getFlipFilterAccess().getOrientationVKeyword_1_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFlipFilterRule());
                    						}
                    						setWithLastConsumed(current, "orientation", lv_orientation_1_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalVideoGen.g:1088:6: lv_orientation_1_4= 'vertical'
                    {
                    lv_orientation_1_4=(Token)match(input,42,FOLLOW_2); 

                    						newLeafNode(lv_orientation_1_4, grammarAccess.getFlipFilterAccess().getOrientationVerticalKeyword_1_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getFlipFilterRule());
                    						}
                    						setWithLastConsumed(current, "orientation", lv_orientation_1_4, null);
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFlipFilter"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000000E0020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000900000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000900020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000000001F000020L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000001E000020L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000001C000020L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000018000020L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000007000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000E00000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000180000020L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000100000020L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000078000000000L});

}