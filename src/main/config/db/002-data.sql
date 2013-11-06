INSERT INTO KD13_CONF_SESS_T(SESS_ID, TITLE, SESS_DATE, START_TIME, END_TIME, ROOM, SESS_TYPE_CODE, DESCRIPTION)
VALUES(1, 'Ready, Set, KRAD!', '2013-11-21', '11:00:00', '11:45:00', 'Some Room', 'TECH', 'Desc')
/

INSERT INTO KD13_PRESENTER_T(PRES_ID, NAME, INSTITUTION, TITLE) 
	VALUES('1', 'Eric Westfall', 'Indiana University', 'Enterprise Applications Architect')
/
INSERT INTO KD13_PRESENTER_T(PRES_ID, NAME, INSTITUTION, TITLE) 
	VALUES('2', 'Jonathan Keller', 'UC Davis', 'Systems Architect')
/

INSERT INTO KD13_SESS_PRES_T(SESS_PRES_ID, SESS_ID, PRES_ID, PRIMARY_IND) 
	VALUES(1000, 1, '1', 'Y')
/
INSERT INTO KD13_SESS_PRES_T(SESS_PRES_ID, SESS_ID, PRES_ID, PRIMARY_IND) 
	VALUES(1001, 1, '2', 'N')
/

INSERT INTO KREW_DOC_HDR_S(id) VALUES(0)
/
INSERT INTO KREW_DOC_TYP_T(DOC_TYP_ID, PARNT_ID, DOC_TYP_NM, DOC_TYP_VER_NBR, ACTV_IND, CUR_IND, LBL, 
    PREV_DOC_TYP_VER_NBR, DOC_TYP_DESC, DOC_HDLR_URL, POST_PRCSR, JNDI_URL, BLNKT_APPR_PLCY, ADV_DOC_SRCH_URL, 
    RTE_VER_NBR, NOTIFY_ADDR, APPL_ID, EMAIL_XSL, SEC_XML, VER_NBR, BLNKT_APPR_GRP_ID, RPT_GRP_ID, GRP_ID, 
    HELP_DEF_URL, OBJ_ID, DOC_SEARCH_HELP_URL, DOC_HDR_ID, AUTHORIZER) 
	VALUES(LAST_INSERT_ID(), 2681, 'ConferenceSession', 0, 1, 1, 'Conference Session', 
    NULL, NULL, '${kr.krad.url}/maintenance?methodToCall=docHandler', NULL, NULL, NULL, NULL, 
    '2', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, 
    NULL, UUID(), NULL, NULL, NULL)
/
