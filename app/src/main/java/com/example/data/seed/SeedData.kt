package com.example.data.seed

import com.example.data.local.BadgeEntity
import com.example.data.local.ConversationEntity
import com.example.data.local.LessonEntity
import com.example.data.local.QuizQuestionEntity
import com.example.data.local.UserProfileEntity
import com.example.data.local.VocabularyEntity

object SeedData {
    val initialProfile = UserProfileEntity(
        id = 1,
        name = "Aman Kumar",
        email = "aman.english@gmail.com",
        targetGoal = "Fluent in 60 Days",
        dailyGoalMinutes = 20,
        minutesSpentToday = 12,
        currentStreak = 6,
        highestStreak = 15,
        xpPoints = 850,
        levelName = "Basic to Fluent",
        isSignedIn = true
    )

    val initialBadges = listOf(
        BadgeEntity(
            id = "b1",
            titleEn = "First Step (पहला कदम)",
            titleHi = "पहली क्लास पूरी की",
            descriptionHi = "English Master पर अपनी पहली क्लास सफलता से पूरी करें।",
            iconType = "star",
            isUnlocked = true,
            progress = 1.0f
        ),
        BadgeEntity(
            id = "b2",
            titleEn = "Vocab Starter (शब्दकोश)",
            titleHi = "10 नए शब्द सीखे",
            descriptionHi = "प्रतिदिन 10 नए अंग्रेजी शब्दों के अर्थ व उच्चारण सीखें।",
            iconType = "book",
            isUnlocked = true,
            progress = 1.0f
        ),
        BadgeEntity(
            id = "b3",
            titleEn = "7-Day Streak Fire (ज्वाला)",
            titleHi = "7 दिन लगातार अभ्यास",
            descriptionHi = "लगातार 7 दिन तक रोज़ाना कम से कम 10 मिनट पढ़ाई करें।",
            iconType = "flame",
            isUnlocked = false,
            progress = 0.85f
        ),
        BadgeEntity(
            id = "b4",
            titleEn = "Grammar Master (व्याकरण गुरु)",
            titleHi = "व्याकरण के 5 नियम सीखे",
            descriptionHi = "Tenses और Parts of Speech के 5 पाठ पूरे करें।",
            iconType = "school",
            isUnlocked = true,
            progress = 1.0f
        ),
        BadgeEntity(
            id = "b5",
            titleEn = "Speaking Star (बोलने का दम)",
            titleHi = "AI से 5 बार बात की",
            descriptionHi = "बोलकर वाक्य सही करें और उच्चारण सुधारें।",
            iconType = "mic",
            isUnlocked = false,
            progress = 0.60f
        ),
        BadgeEntity(
            id = "b6",
            titleEn = "Quiz Champion (क्विज़ विजेता)",
            titleHi = "क्विज़ में 100% स्कोर",
            descriptionHi = "डेली क्विज़ में बिना किसी गलती के सभी उत्तर सही दें।",
            iconType = "trophy",
            isUnlocked = false,
            progress = 0.70f
        )
    )

    val initialLessons = listOf(
        // COURSE: Beginner
        LessonEntity(
            id = "crs_beg_1",
            level = "Beginner",
            moduleType = "COURSE",
            titleEn = "Self Introduction & Greetings",
            titleHi = "अपना परिचय और नमस्ते कहना सीखें",
            summaryHi = "किसी से पहली बार मिलने पर कैसे बात शुरू करें।",
            conceptHi = "अंग्रेजी में बातचीत हमेशा अभिवादन (Greetings) से शुरू होती है। समय के अनुसार 'Good Morning', 'Good Afternoon' या 'Good Evening' कहें।",
            formula = "Greeting + Name + Where you are from + Profession",
            rulesJson = "दोपहर 12 बजे तक: Good Morning||दोपहर 12 से शाम 5 तक: Good Afternoon||शाम 5 के बाद: Good Evening||'Myself Rahul' बोलना व्याकरण की दृष्टि से अमान्य है, हमेशा 'I am Rahul' या 'My name is Rahul' कहें।",
            examplesJson = "Hello! My name is Rahul.::नमस्ते! मेरा नाम राहुल है।::हेलो! माय नेम इज़ राहुल।||I am from Jaipur, Rajasthan.::मैं जयपुर, राजस्थान से हूँ।::आई एम फ्रॉम जयपुर, राजस्थान।||Nice to meet you!::आपसे मिलकर बहुत खुशी हुई!::नाइस टू मीट यू!||I am a college student.::मैं एक कॉलेज का छात्र हूँ।::आई एम अ कॉलेज स्टूडेंट।",
            isCompleted = true,
            isBookmarked = true,
            orderIndex = 1
        ),
        LessonEntity(
            id = "crs_beg_2",
            level = "Beginner",
            moduleType = "COURSE",
            titleEn = "Magic Words: Please, Thank You, Sorry",
            titleHi = "जादुई शब्द: शिष्टाचार की भाषा",
            summaryHi = "सभ्य और प्रभावशाली अंग्रेजी बोलने के तीन सबसे महत्वपूर्ण शब्द।",
            conceptHi = "अंग्रेजी में सम्मान और विनम्रता दिखाने के लिए Please, Thank you, Sorry और Excuse me का बहुत महत्व है।",
            formula = "Please + Action / Thank you + For + Something",
            rulesJson = "मदद माँगते समय हमेशा 'Please' जोड़ें।||किसी से मदद या सलाह मिलने पर 'Thank you so much' बोलें।||गलती होने पर या किसी का ध्यान खींचने पर 'Excuse me' या 'Sorry' कहें।",
            examplesJson = "Could you please help me?::क्या आप कृपया मेरी मदद कर सकते हैं?::कुड यू प्लीज हेल्प मी?||Thank you very much for your advice.::आपकी सलाह के लिए बहुत धन्यवाद।::थैंक यू वेरी मच फॉर योर एडवाइस।||I am sorry for the delay.::देरी के लिए मुझे खेद है।::आई एम सॉरी फॉर द डिले।||Excuse me, where is the metro station?::माफ़ कीजिए, मेट्रो स्टेशन कहाँ है?::एक्सक्यूज़ मी, वेयर इज़ द मेट्रो स्टेशन?",
            isCompleted = true,
            isBookmarked = false,
            orderIndex = 2
        ),
        // COURSE: Basic
        LessonEntity(
            id = "crs_bas_1",
            level = "Basic",
            moduleType = "COURSE",
            titleEn = "Daily Routine in English",
            titleHi = "अपनी दिनचर्या अंग्रेजी में बताना",
            summaryHi = "सुबह उठने से लेकर रात सोने तक के काम कैसे बताएँ।",
            conceptHi = "अपनी रोज़मर्रा की आदतें और दिनचर्या बताने के लिए Simple Present Tense का उपयोग किया जाता है। इसमें Verb की 1st form लगती है।",
            formula = "Subject (I) + Verb 1st Form + Time/Object",
            rulesJson = "I, You, We, They के साथ क्रिया में s/es नहीं लगता। (e.g. I wake up)||He, She, It के साथ s/es जुड़ता है। (e.g. He wakes up)||समय बताने के लिए 'at' Preposition का इस्तेमाल करें। (at 6 AM)",
            examplesJson = "I wake up at 6:00 AM every morning.::मैं रोज़ सुबह 6 बजे उठता हूँ।::आई वेक अप एट सिक्स एएम एव्री मॉर्निंग।||I brush my teeth and drink warm water.::मैं ब्रश करता हूँ और गुनगुना पानी पीता हूँ।::आई ब्रश माय टीथ एंड ड्रिंक वार्म वाटर।||I leave for college at 8:30 AM.::मैं सुबह 8:30 बजे कॉलेज के लिए निकलता हूँ।::आई लीव फॉर कॉलेज एट एइट थर्टी एएम।||I go to bed at 10:30 PM.::मैं रात 10:30 बजे सोने जाता हूँ।::आई गो टू बेड एट टेन थर्टी पीएम।",
            isCompleted = false,
            isBookmarked = true,
            orderIndex = 3
        ),
        LessonEntity(
            id = "crs_bas_2",
            level = "Basic",
            moduleType = "COURSE",
            titleEn = "Asking Questions with Wh- Family",
            titleHi = "Wh- शब्दों से सवाल पूछना सीखें",
            summaryHi = "What, Where, When, Why, Who और How का सही प्रयोग।",
            conceptHi = "जानकारी हासिल करने के लिए Wh- Questions बनाए जाते हैं। जैसे कहाँ, कब, क्यों, कैसे।",
            formula = "Wh- Word + Helping Verb (do/does/is/are) + Subject + Main Verb?",
            rulesJson = "Where = कहाँ (जगह के लिए)||When = कब (समय के लिए)||Why = क्यों (कारण के लिए)||Who = कौन (व्यक्ति के लिए)||How = कैसे (तरीके के लिए)",
            examplesJson = "What do you do?::आप क्या काम करते हैं?::व्हाट डू यू डू?||Where do you live in Delhi?::आप दिल्ली में कहाँ रहते हैं?::वेयर डू यू लिव इन डेल्ही?||Why are you learning English?::आप अंग्रेजी क्यों सीख रहे हैं?::वाय आर यू लर्निंग इंग्लिश?||How much does this cost?::इसकी क्या कीमत है?::हाउ मच डज़ दिस कॉस्ट?",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 4
        ),
        // COURSE: Intermediate
        LessonEntity(
            id = "crs_int_1",
            level = "Intermediate",
            moduleType = "COURSE",
            titleEn = "Modal Verbs: Can, Could, Should, Must",
            titleHi = "Modal Verbs का अचूक इस्तेमाल",
            summaryHi = "क्षमता, सलाह, विनम्र निवेदन और अनिवार्यता व्यक्त करें।",
            conceptHi = "Modal Verbs मुख्य क्रिया के अर्थ को बदल देते हैं। इनके बाद हमेशा Verb की 1st form आती है।",
            formula = "Subject + Modal Verb (Can/Should/Must) + V1 + Object",
            rulesJson = "Can = क्षमता (I can speak English)||Could = भूतकाल की क्षमता या विनम्र प्रार्थना (Could you please open the door?)||Should = सलाह/कर्तव्य (You should sleep early)||Must = सख्त ज़रूरत या पक्का नियम (You must wear a helmet)",
            examplesJson = "I can speak English fluently now.::मैं अब फर्राटेदार अंग्रेजी बोल सकता हूँ।::आई कैन स्पीक इंग्लिश फ्लूएंटली नाउ।||Could you please repeat that?::क्या आप कृपया उसे दोबारा दोहरा सकते हैं?::कुड यू प्लीज रिपीट दैट?||You should drink more water.::आपको अधिक पानी पीना चाहिए।::यू शुड ड्रिंक मोर वाटर।||We must follow the traffic rules.::हमें ट्रैफिक नियमों का पालन अवश्य करना चाहिए।::वी मस्ट फॉलो द ट्रैफिक रूल्स।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 5
        ),
        LessonEntity(
            id = "crs_int_2",
            level = "Intermediate",
            moduleType = "COURSE",
            titleEn = "Expressing Opinions & Disagreeing Politely",
            titleHi = "अपनी राय देना और विनम्रता से असहमत होना",
            summaryHi = "ऑफिस मीटिंग्स और दोस्तों के बीच प्रभावशाली तरीके से विचार रखें।",
            conceptHi = "सीधे 'You are wrong' कहने के बजाय विनम्र वाक्यांशों का इस्तेमाल करें ताकि बातचीत सौहार्दपूर्ण रहे।",
            formula = "In my opinion / I see your point, but...",
            rulesJson = "'I think' के अलावा 'From my perspective', 'In my view' का इस्तेमाल करें।||असहमत होने पर कहें: 'I understand what you mean, but I have a slightly different view.'",
            examplesJson = "In my opinion, practical learning is better.::मेरी राय में, व्यावहारिक सीखना बेहतर है।::इन माय ओपिनियन, प्रैक्टिकल लर्निंग इज़ बेटर।||I agree with you completely.::मैं आपसे पूरी तरह सहमत हूँ।::आई अग्री विद यू कम्प्लीटली।||I see your point, but consider this alternative.::मैं आपकी बात समझता हूँ, पर इस विकल्प पर भी सोचें।::आई सी योर पॉइंट, बट कन्सिडर दिस आल्टरनेटिव।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 6
        ),
        // COURSE: Advanced
        LessonEntity(
            id = "crs_adv_1",
            level = "Advanced",
            moduleType = "COURSE",
            titleEn = "Idioms & Smart Phrases for Daily Life",
            titleHi = "मुहावरे और स्मार्ट वाक्यांश",
            summaryHi = "देशी वक्ताओं की तरह नेचुरल और स्टाइलिश अंग्रेजी बोलें।",
            conceptHi = "Idioms ऐसे मुहावरेदार वाक्यांश होते हैं जिनका शाब्दिक अर्थ अलग और वास्तविक भाव अलग होता है।",
            formula = "Idiom in Context",
            rulesJson = "Piece of cake = बहुत आसान काम||Break a leg = शुभकामनाएँ (Good luck)||Once in a blue moon = कभी-कभार (बहुत दुर्लभ)||Under the weather = अस्वस्थ/तबीयत ठीक न होना",
            examplesJson = "This English exam was a piece of cake!::यह अंग्रेजी परीक्षा बेहद आसान थी!::दिस इंग्लिश एग्जाम वॉज़ अ पीस ऑफ़ केक!||I am feeling a bit under the weather today.::मेरी आज थोड़ी तबीयत नासाज़ है।::आई एम फीलिंग अ बिट अंडर द वेदर टुडे।||He visits his village once in a blue moon.::वह कभी-कभार ही अपने गाँव जाता है।::ही विज़िट्स हिज़ विलेज वन्स इन अ ब्लू मून।||Break a leg for your job interview tomorrow!::कल आपके जॉब इंटरव्यू के लिए ढेर सारी शुभकामनाएँ!::ब्रेक अ लेग फॉर योर जॉब इंटरव्यू टुमॉरो!",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 7
        ),
        LessonEntity(
            id = "crs_adv_2",
            level = "Advanced",
            moduleType = "COURSE",
            titleEn = "Business English & Professional Emails",
            titleHi = "व्यावसायिक अंग्रेजी और ईमेल ड्राफ्टिंग",
            summaryHi = "कॉर्पोरेट जगत, सीनियर्स और क्लाइंट्स से पेशेवर संवाद करना सीखें।",
            conceptHi = "पेशेवर अंग्रेजी में स्पष्टता, संक्षिप्तता और औपचारिकता (Formal Tone) का ध्यान रखा जाता है।",
            formula = "Salutation + Context + Action Point + Closing",
            rulesJson = "शुरुआत: 'I hope this email finds you well.'||फॉलो-अप: 'I am writing to follow up on our earlier discussion.'||समापन: 'Looking forward to hearing from you. Warm regards.'",
            examplesJson = "Please find the attached report for your review.::कृपया अपने अवलोकन के लिए संलग्न रिपोर्ट देखें।::प्लीज फाइंड द अटैच्ड रिपोर्ट फॉर योर रिव्यू।||I would like to schedule a quick meeting tomorrow.::मैं कल एक छोटी बैठक तय करना चाहूँगा।::आई वुड लाइक टू शेड्यूल अ क्विक मीटिंग टुमॉरो।||Thank you for your prompt response.::आपके त्वरित उत्तर के लिए धन्यवाद।::थैंक यू फॉर योर प्रॉम्ट रिस्पॉन्स।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 8
        ),

        // GRAMMAR MODULES (All 9 requested modules)
        LessonEntity(
            id = "grm_1",
            level = "Basic",
            moduleType = "GRAMMAR",
            titleEn = "1. Noun (संज्ञा)",
            titleHi = "संज्ञा और उसके प्रकार",
            summaryHi = "किसी व्यक्ति, वस्तु, स्थान, गुण या भाव के नाम को Noun कहते हैं।",
            conceptHi = "A Noun is the name of a person, place, animal, thing, or idea. उदाहरण: Rohan (व्यक्ति), Delhi (स्थान), Table (वस्तु), Honesty (गुण)।",
            formula = "Proper, Common, Collective, Abstract, Material Nouns",
            rulesJson = "1. Proper Noun (व्यक्तिवाचक): हमेशा Capital Letter से शुरू होता है (e.g. India, Priya)||2. Common Noun (जातिवाचक): पूरी जाति को दर्शाता है (e.g. boy, city, teacher)||3. Collective Noun (समूहवाचक): समूह के लिए एक शब्द (e.g. army, team, crowd)||4. Abstract Noun (भाववाचक): जिसे छू नहीं सकते, केवल महसूस कर सकते हैं (e.g. love, courage, happiness)",
            examplesJson = "Karan is an intelligent student.::करन एक बुद्धिमान छात्र है। (Karan=Proper, student=Common)::करन इज़ एन इंटेलिजेंट स्टूडेंट।||Our cricket team won the match.::हमारी क्रिकेट टीम मैच जीत गई। (Team=Collective)::आवर क्रिकेट टीम वॉन द मैच।||Honesty is the best policy.::ईमानदारी सबसे अच्छी नीति है। (Honesty=Abstract)::ऑनेस्टी इज़ द बेस्ट पॉलिसी।",
            isCompleted = true,
            isBookmarked = true,
            orderIndex = 10
        ),
        LessonEntity(
            id = "grm_2",
            level = "Basic",
            moduleType = "GRAMMAR",
            titleEn = "2. Pronoun (सर्वनाम)",
            titleHi = "सर्वनाम और उसके रूप",
            summaryHi = "संज्ञा (Noun) के दोहराव से बचने के लिए उसकी जगह आने वाले शब्द।",
            conceptHi = "A Pronoun is a word used instead of a Noun. जैसे: Rohan is a boy. He is smart. यहाँ Rohan की जगह 'He' का प्रयोग हुआ।",
            formula = "Subject Pronoun vs Object Pronoun vs Possessive",
            rulesJson = "Subject रूप: I, You, He, She, It, We, They||Object रूप: Me, You, Him, Her, It, Us, Them||Possessive (अधिकार): My/Mine, Your/Yours, His, Her/Hers, Our/Ours, Their/Theirs||Reflexive: Myself, Yourself, Himself, Themselves",
            examplesJson = "She told him about the new project.::उसने उसे नए प्रोजेक्ट के बारे में बताया।::शी टोल्ड हिम अबाउट द न्यू प्रोजेक्ट।||This blue pen is mine, not yours.::यह नीली कलम मेरी है, तुम्हारी नहीं।::दिस ब्लू पेन इज़ माइन, नॉट योर्स।||They solved the problem themselves.::उन्होंने खुद ही समस्या का समाधान किया।::दे सॉल्वेड द प्रॉब्लम देमसेल्व्ज़।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 11
        ),
        LessonEntity(
            id = "grm_3",
            level = "Basic",
            moduleType = "GRAMMAR",
            titleEn = "3. Verb (क्रिया)",
            titleHi = "क्रिया और Helping Verbs",
            summaryHi = "किसी कार्य (Action) या स्थिति (State) को बताने वाला शब्द।",
            conceptHi = "A Verb expresses an action, occurrence, or state of being. वाक्य में बिना Verb के कोई अर्थ नहीं बनता।",
            formula = "Subject + Helping Verb (HV) + Main Verb (MV)",
            rulesJson = "Main Verb के 3 रूप: V1 (Present), V2 (Past), V3 (Past Participle)||उदाहरण: Go - Went - Gone, Speak - Spoke - Spoken, Write - Wrote - Written||Helping Verbs: Is, Am, Are, Was, Were, Has, Have, Had, Will, Shall",
            examplesJson = "The children are playing football in the park.::बच्चे पार्क में फुटबॉल खेल रहे हैं।::द चिल्ड्रेन आर प्लेइंग फुटबॉल इन द पार्क।||I have finished my homework.::मैंने अपना गृहकार्य पूरा कर लिया है।::आई हैव फिनिश्ड माय होमवर्क।||She wrote a beautiful poem.::उसने एक सुंदर कविता लिखी।::शी रोट अ ब्यूटीफुल पोएम।",
            isCompleted = false,
            isBookmarked = true,
            orderIndex = 12
        ),
        LessonEntity(
            id = "grm_4",
            level = "Basic",
            moduleType = "GRAMMAR",
            titleEn = "4. Adjective (विशेषण)",
            titleHi = "विशेषण और Degrees of Comparison",
            summaryHi = "Noun या Pronoun की विशेषता (गुण, दोष, संख्या, मात्रा) बताने वाले शब्द।",
            conceptHi = "An Adjective modifies or describes a noun or pronoun. जैसे: Red apple, Tall boy, Five mangoes, Sweet song.",
            formula = "Positive -> Comparative (-er / more) -> Superlative (-est / most)",
            rulesJson = "1. Positive: Tall (सरल गुण)||2. Comparative: Taller than (दो लोगों में तुलना)||3. Superlative: The tallest (सबमें सबसे श्रेष्ठ)||बड़े शब्दों में: Beautiful -> More beautiful -> Most beautiful",
            examplesJson = "Pooja is a very polite girl.::पूजा एक बहुत विनम्र लड़की है।::पूजा इज़ अ वेरी पोलाइट गर्ल।||Mount Everest is higher than K2.::माउंट एवरेस्ट K2 से ऊँचा है।::माउंट एवरेस्ट इज़ हायर दैन के-टू।||He is the most hardworking person in our team.::वह हमारी टीम में सबसे मेहनती व्यक्ति है।::ही इज़ द मोस्ट हार्डवर्किंग पर्सन इन आवर टीम।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 13
        ),
        LessonEntity(
            id = "grm_5",
            level = "Intermediate",
            moduleType = "GRAMMAR",
            titleEn = "5. Adverb (क्रिया विशेषण)",
            titleHi = "क्रिया विशेषण का सही प्रयोग",
            summaryHi = "Verb, Adjective या दूसरे Adverb की विशेषता बताने वाला शब्द।",
            conceptHi = "An Adverb tells how, when, where, or to what degree an action happens. अक्सर इनके अंत में '-ly' आता है।",
            formula = "Verb + Adverb of Manner (Quickly, Slowly, Carefully)",
            rulesJson = "Adverb of Manner (तरीका): He speaks softly. (कैसे बोला? धीरे से)||Adverb of Time (समय): I will come tomorrow. (कब आएगा? कल)||Adverb of Place (स्थान): Come here. (कहाँ आओ? यहाँ)||Adverb of Frequency (बारंबारता): Always, Never, Often, Sometimes",
            examplesJson = "He drives very carefully in heavy traffic.::वह भारी ट्रैफिक में बहुत सावधानी से गाड़ी चलाता है।::ही ड्राइव्स वेरी केयरफुली इन हेवी ट्रैफिक।||I always wake up early.::मैं हमेशा जल्दी उठता हूँ।::आई ऑलवेज़ वेक अप अर्ली।||The train arrived unexpectedly early.::ट्रेन अप्रत्याशित रूप से समय से पहले पहुँच गई।::द ट्रेन अराइव्ड अनएक्सपेक्टेडली अर्ली।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 14
        ),
        LessonEntity(
            id = "grm_6",
            level = "Intermediate",
            moduleType = "GRAMMAR",
            titleEn = "6. Tenses (काल - सम्पूर्ण 12 काल)",
            titleHi = "तीनों काल और 12 Tenses का चार्ट",
            summaryHi = "Present, Past और Future के चारों भेद: Simple, Continuous, Perfect, Perfect Continuous.",
            conceptHi = "समय और कार्य की पूर्णता को दर्शाने के लिए Tense का प्रयोग होता है। हिन्दी पहचान और संरचना याद रखें।",
            formula = "Present: S + V1 / is-am-are+V4 / has-have+V3",
            rulesJson = "1. Simple Present (ता है, ती है): S + V1(s/es) + O -> I read books.||2. Present Continuous (रहा है): S + is/am/are + V1+ing + O -> He is eating.||3. Present Perfect (चुका है, लिया है): S + has/have + V3 + O -> She has arrived.||4. Simple Past (था, आया, गया): S + V2 + O -> We went to Delhi.||5. Simple Future (गा, गी, गे): S + will + V1 + O -> I will call you.",
            examplesJson = "I learn English every day.::मैं रोज़ अंग्रेजी सीखता हूँ। (Simple Present)::आई लर्न इंग्लिश एव्री डे।||They are watching a movie right now.::वे अभी एक फिल्म देख रहे हैं। (Continuous)::दे आर वॉचिंग अ मूवी राइट नाउ।||She has completed her MBA.::उसने अपनी एमबीए पूरी कर ली है। (Perfect)::शी हैज़ कम्प्लीटेड हर एमबीए।||We visited the Taj Mahal last month.::हमने पिछले महीने ताजमहल का दौरा किया था। (Past)::वी विज़िटेड द ताज महल लास्ट मंथ।",
            isCompleted = false,
            isBookmarked = true,
            orderIndex = 15
        ),
        LessonEntity(
            id = "grm_7",
            level = "Basic",
            moduleType = "GRAMMAR",
            titleEn = "7. Articles (A, An, The)",
            titleHi = "A, An और The का बिना झिझक प्रयोग",
            summaryHi = "व्यंजन ध्वनि (Consonant Sound) और स्वर ध्वनि (Vowel Sound) का रहस्य।",
            conceptHi = "अंग्रेजी में अक्षरों (A, E, I, O, U) से ज़्यादा उच्चारण की ध्वनि (Hindi Vowel Sounds: अ, आ, इ, ई, उ, ए, ऐ, ओ, औ) देखी जाती है।",
            formula = "A (व्यंजन ध्वनि) / An (स्वर ध्वनि) / The (विशिष्ट/अनोखा)",
            rulesJson = "An University नहीं, 'A University' होगा क्योंकि आवाज़ 'य' (Yu) से शुरू होती है।||'An honest man' होगा क्योंकि 'H' साइलेंट है और आवाज़ 'ऑ' (Honest) से आती है।||'The' का प्रयोग: सूरज, चाँद, प्रसिद्ध नदियाँ, ऐतिहासिक इमारतें और निश्चित वस्तुओं के लिए।",
            examplesJson = "He is an honest police officer.::वह एक ईमानदार पुलिस अधिकारी है।::ही इज़ एन ऑनेस्ट पुलिस ऑफिसर।||I bought an umbrella and a notebook.::मैंने एक छाता और एक नोटबुक खरीदी।::आई बॉट एन अम्ब्रेला एंड अ नोटबुक।||The sun rises in the east.::सूर्य पूर्व में उगता है।::द सन राइज़ेस इन द ईस्ट।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 16
        ),
        LessonEntity(
            id = "grm_8",
            level = "Intermediate",
            moduleType = "GRAMMAR",
            titleEn = "8. Prepositions (सम्बन्ध सूचक अव्यय)",
            titleHi = "In, On, At, Under, Between, Among",
            summaryHi = "स्थान, समय और दिशा बताने वाले सबसे ज़रूरी शब्द।",
            conceptHi = "Preposition वाक्य में Noun या Pronoun का संबंध किसी दूसरे शब्द से जोड़ता है।",
            formula = "Noun + Preposition + Place/Time",
            rulesJson = "At = निश्चित समय या छोटा स्थान (At 5 PM, At the bus stop)||On = दिन/तारीख या सतह पर (On Monday, On the table)||In = महीने/साल या घिरे हुए स्थान में (In 2026, In the room)||Between = केवल दो के बीच में||Among = दो से अधिक के बीच में",
            examplesJson = "The meeting starts at 10:00 AM on Monday.::बैठक सोमवार सुबह 10 बजे शुरू होगी।::द मीटिंग स्टार्ट्स एट टेन एएम ऑन मंडे।||Distribute these sweets among the students.::ये मिठाइयाँ छात्रों में बाँट दो। (दो से अधिक)::डिस्ट्रिब्यूट दीज़ स्वीट्स अमंग द स्टूडेंट्स।||The cat is sleeping under the table.::बिल्ली मेज़ के नीचे सो रही है।::द कैट इज़ स्लीपिंग अंडर द टेबल।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 17
        ),
        LessonEntity(
            id = "grm_9",
            level = "Advanced",
            moduleType = "GRAMMAR",
            titleEn = "9. Active and Passive Voice",
            titleHi = "कर्तृवाच्य व कर्मवाच्य (Active vs Passive)",
            summaryHi = "जब कर्ता (Subject) के बजाय कर्म (Object) पर ज़ोर देना हो।",
            conceptHi = "Active Voice में काम करने वाला मुख्य होता है (Ram wrote a letter)। Passive Voice में काम जिस पर हुआ वह पहले आता है (A letter was written by Ram)।",
            formula = "Active: S + V + O -> Passive: O + Helping Verb + V3 + by + S",
            rulesJson = "1. Passive Voice में हमेशा मुख्य क्रिया की 3rd Form (V3) ही लगती है।||2. Present Simple: is/am/are + V3||3. Past Simple: was/were + V3||4. Continuous: being + V3 जोड़ा जाता है।||5. Perfect: been + V3 जोड़ा जाता है।",
            examplesJson = "Active: She cooks delicious food. -> Passive: Delicious food is cooked by her.::स्वादिष्ट खाना उसके द्वारा पकाया जाता है।::डिलिशियस फूड इज़ कुक्ड बाय हर।||Active: Police arrested the thief. -> Passive: The thief was arrested by police.::चोर को पुलिस द्वारा गिरफ्तार कर लिया गया था।::द थीफ वॉज़ अरेस्टेड बाय पुलिस।||The letter has been sent.::पत्र भेजा जा चुका है।::द लेटर हैज़ बीन सेंट।",
            isCompleted = false,
            isBookmarked = false,
            orderIndex = 18
        )
    )

    val initialVocabulary = listOf(
        // Day 1 / Essential Daily
        VocabularyEntity(
            word = "Perseverance",
            partOfSpeech = "Noun",
            meaningHi = "दृढ़ता, लगन, निरंतर प्रयास",
            pronunciationHi = "पर्सिवियरन्स",
            exampleEn = "Perseverance is key to mastering the English language.",
            exampleHi = "अंग्रेजी भाषा में महारत हासिल करने के लिए निरंतर लगन जरूरी है।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Eloquent",
            partOfSpeech = "Adjective",
            meaningHi = "सुवक्ता, प्रभावशाली बोलने वाला",
            pronunciationHi = "एलोक्वेंट",
            exampleEn = "She gave an eloquent speech at the seminar.",
            exampleHi = "उसने सेमिनार में एक बहुत ही प्रभावशाली भाषण दिया।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Hesitate",
            partOfSpeech = "Verb",
            meaningHi = "हिचकिचाना, संकोच करना",
            pronunciationHi = "हेज़िटेट",
            exampleEn = "Do not hesitate to ask questions during the class.",
            exampleHi = "कक्षा के दौरान सवाल पूछने में संकोच न करें।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Compassionate",
            partOfSpeech = "Adjective",
            meaningHi = "दयालु, करुणामयी",
            pronunciationHi = "कम्पैशनेट",
            exampleEn = "A good doctor is always compassionate towards patients.",
            exampleHi = "एक अच्छा डॉक्टर हमेशा मरीजों के प्रति दयालु होता है।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Fascinating",
            partOfSpeech = "Adjective",
            meaningHi = "आकर्षक, मनमोहक, दिलचस्प",
            pronunciationHi = "फैसिनेटिंग",
            exampleEn = "Space science is a fascinating subject for youth.",
            exampleHi = "अंतरिक्ष विज्ञान युवाओं के लिए एक बेहद आकर्षक विषय है।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Achieve",
            partOfSpeech = "Verb",
            meaningHi = "हासिल करना, प्राप्त करना",
            pronunciationHi = "अचीव",
            exampleEn = "With hard work, you can achieve all your goals.",
            exampleHi = "कड़ी मेहनत से आप अपने सभी लक्ष्य हासिल कर सकते हैं।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Confidential",
            partOfSpeech = "Adjective",
            meaningHi = "गोपनीय, गुप्त",
            pronunciationHi = "कॉन्फिडेंशियल",
            exampleEn = "Please keep this official document confidential.",
            exampleHi = "कृपया इस आधिकारिक दस्तावेज़ को गोपनीय रखें।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Optimistic",
            partOfSpeech = "Adjective",
            meaningHi = "आशावादी, सकारात्मक सोच वाला",
            pronunciationHi = "ऑप्टिमिस्टिक",
            exampleEn = "Stay optimistic even in difficult situations.",
            exampleHi = "मुश्किल परिस्थितियों में भी आशावादी बने रहें।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Punctual",
            partOfSpeech = "Adjective",
            meaningHi = "समय का पाबंद",
            pronunciationHi = "पंक्चुअल",
            exampleEn = "He is always punctual and arrives before time.",
            exampleHi = "वह हमेशा समय का पाबंद है और वक्त से पहले पहुँचता है।",
            category = "Daily 10",
            dayNumber = 1
        ),
        VocabularyEntity(
            word = "Opportunity",
            partOfSpeech = "Noun",
            meaningHi = "अवसर, मौका",
            pronunciationHi = "अपॉर्च्युनिटी",
            exampleEn = "This internship is a golden opportunity for your career.",
            exampleHi = "यह इंटर्नशिप आपके करियर के लिए एक सुनहरा अवसर है।",
            category = "Daily 10",
            dayNumber = 1
        ),

        // Business & Office
        VocabularyEntity(
            word = "Negotiate",
            partOfSpeech = "Verb",
            meaningHi = "मोलभाव करना, बातचीत से समझौता करना",
            pronunciationHi = "नेगोशिएट",
            exampleEn = "They managed to negotiate a better deal with suppliers.",
            exampleHi = "वे आपूर्तिकर्ताओं के साथ बेहतर सौदे पर बातचीत करने में सफल रहे।",
            category = "Business",
            dayNumber = 2
        ),
        VocabularyEntity(
            word = "Collaborate",
            partOfSpeech = "Verb",
            meaningHi = "मिलकर काम करना, सहयोग करना",
            pronunciationHi = "कोलैबोरेट",
            exampleEn = "Our team will collaborate with designers on this project.",
            exampleHi = "हमारी टीम इस प्रोजेक्ट पर डिज़ाइनरों के साथ मिलकर काम करेगी।",
            category = "Business",
            dayNumber = 2
        ),
        VocabularyEntity(
            word = "Deadline",
            partOfSpeech = "Noun",
            meaningHi = "अंतिम समय सीमा",
            pronunciationHi = "डेडलाइन",
            exampleEn = "We must submit the final presentation before Friday's deadline.",
            exampleHi = "हमें शुक्रवार की अंतिम समय सीमा से पहले प्रस्तुति जमा करनी होगी।",
            category = "Business",
            dayNumber = 2
        ),
        VocabularyEntity(
            word = "Feedback",
            partOfSpeech = "Noun",
            meaningHi = "प्रतिक्रिया, राय",
            pronunciationHi = "फीडबैक",
            exampleEn = "Constructive feedback helps in personal growth.",
            exampleHi = "रचनात्मक प्रतिक्रिया व्यक्तिगत विकास में मदद करती है।",
            category = "Business",
            dayNumber = 2
        ),

        // Emotions & Feelings
        VocabularyEntity(
            word = "Grateful",
            partOfSpeech = "Adjective",
            meaningHi = "आभारी, कृतज्ञ",
            pronunciationHi = "ग्रेटफुल",
            exampleEn = "I am deeply grateful for your generous support.",
            exampleHi = "मैं आपके उदार सहयोग के लिए बहुत आभारी हूँ।",
            category = "Emotions",
            dayNumber = 3
        ),
        VocabularyEntity(
            word = "Anxious",
            partOfSpeech = "Adjective",
            meaningHi = "चिंतित, बेचैन",
            pronunciationHi = "एंक्शस",
            exampleEn = "Don't feel anxious before the interview, just take a deep breath.",
            exampleHi = "इंटरव्यू से पहले बेचैन न हों, बस एक गहरी सांस लें।",
            category = "Emotions",
            dayNumber = 3
        ),
        VocabularyEntity(
            word = "Delighted",
            partOfSpeech = "Adjective",
            meaningHi = "अत्यधिक प्रसन्न, गदगद",
            pronunciationHi = "डिलाइटेड",
            exampleEn = "We were delighted to hear about your promotion.",
            exampleHi = "आपकी पदोन्नति की खबर सुनकर हम बेहद प्रसन्न हुए।",
            category = "Emotions",
            dayNumber = 3
        ),

        // Travel & Airport
        VocabularyEntity(
            word = "Departure",
            partOfSpeech = "Noun",
            meaningHi = "प्रस्थान, रवानगी",
            pronunciationHi = "डिपार्चर",
            exampleEn = "The flight's departure is scheduled for 8:15 PM.",
            exampleHi = "उड़ान का प्रस्थान रात 8:15 बजे निर्धारित है।",
            category = "Travel",
            dayNumber = 4
        ),
        VocabularyEntity(
            word = "Destination",
            partOfSpeech = "Noun",
            meaningHi = "मंजिल, गंतव्य स्थल",
            pronunciationHi = "डेस्टिनेशन",
            exampleEn = "Goa is a popular holiday destination in India.",
            exampleHi = "गोवा भारत में छुट्टियों के लिए एक लोकप्रिय गंतव्य स्थल है।",
            category = "Travel",
            dayNumber = 4
        ),
        VocabularyEntity(
            word = "Luggage",
            partOfSpeech = "Noun",
            meaningHi = "सामान, असबाब",
            pronunciationHi = "लगेज",
            exampleEn = "Keep an eye on your luggage at the train station.",
            exampleHi = "रेलवे स्टेशन पर अपने सामान पर नज़र रखें।",
            category = "Travel",
            dayNumber = 4
        )
    )

    val initialConversations = listOf(
        ConversationEntity(
            id = "conv_school",
            category = "School",
            titleEn = "Asking the Teacher for Leave",
            titleHi = "शिक्षक से छुट्टी के लिए प्रार्थना करना",
            situationHi = "स्कूल या कॉलेज में शिक्षक से अवकाश (Leave) माँगने की औपचारिक बातचीत।",
            turnsJson = "Student::Good morning, Sir! May I come in?::नमस्ते सर! क्या मैं अंदर आ सकता हूँ?::गुड मॉर्निंग, सर! मे आई कम इन?||Teacher::Good morning, Rahul. Yes, please come in. What is the matter?::नमस्ते राहुल। हाँ, अंदर आ जाओ। क्या बात है?::गुड मॉर्निंग, राहुल। यस, प्लीज कम इन। व्हाट इज़ द मैटर?||Student::Sir, I have high fever today. Could you please grant me leave for two days?::सर, मुझे आज तेज बुखार है। क्या आप कृपया मुझे दो दिन का अवकाश दे सकते हैं?::सर, आई हैव हाई फीवर टुडे। कुड यू प्लीज ग्रांट मी लीव फॉर टू डेज़?||Teacher::Oh, take care! Please submit an application letter, and rest well.::ओह, अपना ध्यान रखो! कृपया एक प्रार्थना पत्र जमा कर दो और आराम करो।::ओह, टेक केयर! प्लीज सबमिट एन एप्लीकेशन लेटर, एंड रेस्ट वेल।||Student::Thank you so much, Sir! I will submit it right away.::बहुत धन्यवाद, सर! मैं इसे तुरंत जमा कर देता हूँ।::थैंक यू सो मच, सर! आई विल सबमिट इट राइट अवे।"
        ),
        ConversationEntity(
            id = "conv_shop",
            category = "Shopping",
            titleEn = "Buying Clothes & Asking for Price",
            titleHi = "कपड़ों की दुकान पर खरीदारी व मोलभाव",
            situationHi = "दुकानदार से कपड़े देखना, साइज पूछना और दाम कम कराने की बातचीत।",
            turnsJson = "Customer::Excuse me, do you have this blue shirt in medium size?::माफ़ कीजिए, क्या आपके पास यह नीली शर्ट मीडियम साइज में है?::एक्सक्यूज़ मी, डू यू हैव दिस ब्लू शर्ट इन मीडियम साइज?||Shopkeeper::Yes, absolutely! Here is the medium size in cotton fabric.::हाँ बिल्कुल! यह रही कॉटन फैब्रिक में मीडियम साइज।::यस, एब्सोल्यूटली! हियर इज़ द मीडियम साइज इन कॉटन फैब्रिक।||Customer::How much does this shirt cost?::इस शर्ट की क्या कीमत है?::हाउ मच डज़ दिस शर्ट कॉस्ट?||Shopkeeper::It is twelve hundred rupees only, Sir.::यह केवल बारह सौ रुपये की है, सर।::इट इज़ ट्वेल्व हंड्रेड रुपीज़ ओनली, सर।||Customer::Can you offer any discount if I buy two shirts?::यदि मैं दो शर्ट खरीदूँ तो क्या आप कोई छूट दे सकते हैं?::कैन यू ऑफर एनी डिस्काउंट इफ आई बाय टू शर्ट्स?||Shopkeeper::Sure, I can give you a 15% discount on both!::ज़रूर, मैं दोनों पर आपको 15% की छूट दे सकता हूँ!::श्योर, आई कैन गिव यू अ फिफ्टीन परसेंट डिस्काउंट ऑन बोथ!"
        ),
        ConversationEntity(
            id = "conv_interview",
            category = "Interview",
            titleEn = "Job Interview Icebreaker",
            titleHi = "जॉब इंटरव्यू में पहला परिचय",
            situationHi = "इंटरव्यूअर के सामने आत्मविश्वास के साथ अपना परिचय देना।",
            turnsJson = "Interviewer::Welcome! Please have a seat. Could you tell me a little about yourself?::स्वागत है! कृपया बैठिए। क्या आप अपने बारे में थोड़ा बता सकते हैं?::वेलकम! प्लीज हैव अ सीट। कुड यू टेल मी अ लिटिल अबाउट योरसेल्फ?||Candidate::Good morning, Sir! Thank you for this opportunity. My name is Aman, and I completed my graduation in Computer Science.::शुभ प्रभात, सर! इस अवसर के लिए धन्यवाद। मेरा नाम अमन है, और मैंने कंप्यूटर साइंस में ग्रेजुएशन पूरा किया है।::गुड मॉर्निंग, सर! थैंक यू फॉर दिस अपॉर्च्युनिटी। माय नेम इज़ अमन, एंड आई कम्प्लीटेड माय ग्रेजुएशन इन कंप्यूटर साइंस।||Interviewer::That's great! What are your key strengths?::बहुत बढ़िया! आपकी मुख्य खूबियाँ क्या हैं?::दैट्स ग्रेट! व्हाट आर योर की स्ट्रेंथ्स?||Candidate::I am a quick learner, dedicated to deadlines, and a great team player.::मैं चीज़ें बहुत जल्दी सीखता हूँ, समय सीमा के प्रति समर्पित हूँ, और टीम में अच्छा काम करता हूँ।::आई एम अ क्विक लर्नर, डेडिकेटेड टू डेडलाइंस, एंड अ ग्रेट टीम प्लेयर।||Interviewer::Impressive! Why do you want to join our company?::प्रभावशाली! आप हमारी कंपनी से क्यों जुड़ना चाहते हैं?::इम्प्रेसिव! वाय डू यू वांट टू जॉइन आवर कंपनी?"
        ),
        ConversationEntity(
            id = "conv_office",
            category = "Office",
            titleEn = "Project Update with Colleague",
            titleHi = "ऑफिस में सहकर्मी से काम पर चर्चा",
            situationHi = "प्रोजेक्ट की प्रगति और मीटिंग के समय पर बात करना।",
            turnsJson = "Priya::Hi Rohan, did you finish the client presentation?::हाय रोहन, क्या तुमने क्लाइंट प्रेजेंटेशन पूरी कर ली?::हाय रोहन, डिड यू फिनिश द क्लाइंट प्रेजेंटेशन?||Rohan::Hi Priya! Yes, I just emailed the final slides to the manager.::हाय प्रिया! हाँ, मैंने अभी-अभी मैनेजर को फाइनल स्लाइड्स ईमेल की हैं।::हाय प्रिया! यस, आई जस्ट ईमेल्ड द फाइनल स्लाइड्स टू द मैनेजर।||Priya::Awesome. What time is the conference call today?::शानदार। आज कॉन्फ्रेंस कॉल कितने बजे है?::ऑसम। व्हाट टाइम इज़ द कॉन्फ्रेंस कॉल टुडे?||Rohan::It is scheduled for 3:00 PM in meeting room B.::यह मीटिंग रूम बी में दोपहर 3 बजे तय है।::इट इज़ शेड्यूल फॉर थ्री पीएम इन मीटिंग रूम बी।"
        ),
        ConversationEntity(
            id = "conv_travel",
            category = "Travel",
            titleEn = "Asking for Directions at the Metro",
            titleHi = "मेट्रो स्टेशन पर रास्ता पूछना",
            situationHi = "अपरिचित शहर में सही प्लेटफॉर्म या रूट पूछना।",
            turnsJson = "Traveler::Excuse me, which train goes towards Connaught Place?::माफ़ कीजिए, कौन सी ट्रेन कनॉट प्लेस की तरफ जाती है?::एक्सक्यूज़ मी, व्हिच ट्रेन गोज़ टुवर्ड्स कनॉट प्लेस?||Local::Take the Yellow Line from Platform number 2.::प्लेटफॉर्म नंबर 2 से येलो लाइन पकड़िए।::टेक द येलो लाइन फ्रॉम प्लेटफॉर्म नंबर टू।||Traveler::How many stations is that from here?::यहाँ से कितने स्टेशन पड़ेंगे?::हाउ मेनी स्टेशन्स इज़ दैट फ्रॉम हियर?||Local::It is just four stations away. It takes about 10 minutes.::यह केवल चार स्टेशन दूर है। लगभग 10 मिनट लगेंगे।::इट इज़ जस्ट फोर स्टेशन्स अवे। इट टेक्स अबाउट टेन मिनट्स।||Traveler::Thank you very much for your help!::आपकी मदद के लिए बहुत-बहुत धन्यवाद!::थैंक यू वेरी मच फॉर योर हेल्प!"
        ),
        ConversationEntity(
            id = "conv_family",
            category = "Family",
            titleEn = "Dinner Table & Weekend Plans",
            titleHi = "रात के खाने पर परिवार के साथ बातचीत",
            situationHi = "सप्ताहांत की छुट्टी और खाने की योजना बनाना।",
            turnsJson = "Mom::Dinner is ready! Please wash your hands and come to the table.::रात का खाना तैयार है! कृपया हाथ धोकर मेज़ पर आ जाइए।::डिनर इज़ रेडी! प्लीज वॉश योर हैंड्स एंड कम टू द टेबल।||Son::The food smells delicious, Mom! What have you cooked?::खाने की खुशबू बहुत लाजवाब है, माँ! आपने क्या पकाया है?::द फूड स्मेल्स डिलिशियस, मॉम! व्हाट हैव यू कुक्ड?||Mom::I made paneer butter masala and fresh rotis.::मैंने पनीर बटर मसाला और ताज़ा रोटियाँ बनाई हैं।::आई मेड पनीर बटर मसाला एंड फ्रेश रोटीज़।||Son::Can we go to the science park this Sunday?::क्या हम इस रविवार साइंस पार्क जा सकते हैं?::कैन वी गो टू द साइंस पार्क दिस संडे?"
        )
    )

    val initialQuizzes = listOf(
        // Daily MCQ
        QuizQuestionEntity(
            category = "Daily",
            level = "Beginner",
            questionEn = "Choose the correct greeting for 9:00 AM in the morning:",
            questionHi = "सुबह 9:00 बजे के लिए सही अभिवादन चुनें:",
            optionsJson = "Good Night||Good Evening||Good Morning||Good Afternoon",
            correctIndex = 2,
            explanationHi = "सुबह 12:00 बजे दोपहर से पहले हमेशा 'Good Morning' कहा जाता है।"
        ),
        QuizQuestionEntity(
            category = "Daily",
            level = "Beginner",
            questionEn = "Which sentence correctly introduces yourself?",
            questionHi = "कौन सा वाक्य अपना परिचय सही ढंग से देता है?",
            optionsJson = "Myself is Rahul.||My name is Rahul.||I name Rahul.||Me Rahul is.",
            correctIndex = 1,
            explanationHi = "अंग्रेजी में परिचय के लिए 'My name is Rahul' या 'I am Rahul' व्याकरण की दृष्टि से शुद्ध रूप है।"
        ),
        // Grammar Quizzes
        QuizQuestionEntity(
            category = "Grammar",
            level = "Basic",
            questionEn = "Fill in the blank: She _______ to school every day.",
            questionHi = "खाली जगह भरें: She _______ to school every day.",
            optionsJson = "go||goes||going||gone",
            correctIndex = 1,
            explanationHi = "Present Simple में Third person singular (He, She, It) के साथ Verb में s/es लगता है (She goes)।"
        ),
        QuizQuestionEntity(
            category = "Grammar",
            level = "Basic",
            questionEn = "Identify the Adjective in: 'Rohan bought a luxurious car.'",
            questionHi = "वाक्य में विशेषण पहचानें: 'Rohan bought a luxurious car.'",
            optionsJson = "Rohan||bought||luxurious||car",
            correctIndex = 2,
            explanationHi = "'luxurious' (शानदार) यहाँ car (संज्ञा) की विशेषता बता रहा है, इसलिए यह Adjective है।"
        ),
        QuizQuestionEntity(
            category = "Grammar",
            level = "Intermediate",
            questionEn = "Choose the correct article: 'He is _______ honest man.'",
            questionHi = "सही Article चुनें: 'He is _______ honest man.'",
            optionsJson = "a||an||the||no article",
            correctIndex = 1,
            explanationHi = "'Honest' में 'H' साइलेंट है और स्वर ध्वनि 'ऑ' (Vowel sound) से उच्चारण होता है, इसलिए 'an' लगेगा।"
        ),
        QuizQuestionEntity(
            category = "Grammar",
            level = "Intermediate",
            questionEn = "Select the correct Preposition: 'The meeting will start _______ 10:00 AM.'",
            questionHi = "सही Preposition चुनें: 'The meeting will start _______ 10:00 AM.'",
            optionsJson = "in||on||at||by",
            correctIndex = 2,
            explanationHi = "घड़ी के निश्चित समय (Exact clock time) से पहले हमेशा 'at' का प्रयोग होता है।"
        ),
        QuizQuestionEntity(
            category = "Grammar",
            level = "Advanced",
            questionEn = "Passive Voice of 'She wrote a letter' is:",
            questionHi = "'She wrote a letter' का Passive Voice क्या होगा?",
            optionsJson = "A letter is written by her.||A letter was written by her.||A letter has been written by her.||A letter had written by her.",
            correctIndex = 1,
            explanationHi = "Past Simple (wrote) का Passive बनाते समय was/were + V3 (was written) का प्रयोग होता है।"
        ),
        // Vocabulary Quizzes
        QuizQuestionEntity(
            category = "Vocabulary",
            level = "Basic",
            questionEn = "What is the Hindi meaning of 'Hesitate'?",
            questionHi = "'Hesitate' शब्द का हिन्दी अर्थ क्या है?",
            optionsJson = "हिचकिचाना||दौड़ना||मुस्कुराना||लिखना",
            correctIndex = 0,
            explanationHi = "'Hesitate' का अर्थ संकोच करना या हिचकिचाना होता है।"
        ),
        QuizQuestionEntity(
            category = "Vocabulary",
            level = "Intermediate",
            questionEn = "What does the idiom 'Piece of cake' mean?",
            questionHi = "'Piece of cake' मुहावरे का क्या अर्थ होता है?",
            optionsJson = "केक का टुकड़ा||बेहद आसान काम||बहुत स्वादिष्ट भोजन||मुश्किल चुनौती",
            correctIndex = 1,
            explanationHi = "'Piece of cake' का मुहावरेदार अर्थ 'Something very easy and simple' (बेहद आसान कार्य) होता है।"
        ),
        QuizQuestionEntity(
            category = "Speaking",
            level = "Intermediate",
            questionEn = "How do you politely disagree with someone?",
            questionHi = "किसी से विनम्रतापूर्वक असहमति कैसे जताएँ?",
            optionsJson = "You are totally wrong!||Shut up, I don't care.||I see your point, but I have a different perspective.||Never say that again.",
            correctIndex = 2,
            explanationHi = "सभ्य बातचीत में 'I see your point, but...' एक बहुत ही विनम्र और पेशेवर तरीका है।"
        )
    )

    val sampleLeaderboard = listOf(
        com.example.data.model.LeaderboardUser(1, "Aman Kumar (You)", "New Delhi", 6, 850, true),
        com.example.data.model.LeaderboardUser(2, "Pooja Verma", "Lucknow", 14, 820),
        com.example.data.model.LeaderboardUser(3, "Rohit Sharma", "Patna", 9, 780),
        com.example.data.model.LeaderboardUser(4, "Sneha Patel", "Ahmedabad", 11, 740),
        com.example.data.model.LeaderboardUser(5, "Vikram Singh", "Jaipur", 5, 690),
        com.example.data.model.LeaderboardUser(6, "Anjali Gupta", "Indore", 8, 650)
    )
}
