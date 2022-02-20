package groovy_dsl_book.initial

//• The regex match operator, ==~
//• The regex find operator, =~
//• The regex pattern operator, ~String

given: "A String we want to match"
    def matchMe = "Match Me"
expect: "We can do an exact match using single quoted Strings"
    matchMe ==~ 'Match Me'
and: "using multiline style String"
    matchMe ==~ """Match Me"""
and: "using slashy String"
    matchMe ==~ /Match Me/


given: "a dollar slashy"
    def dollarSlashy = $/
    $ dollar
    $$ dollar
    \ backslash
    / slash
    $/ slash
    /$
and: "an old style multiline string"
    def multi = """
    \$ dollar
    \$ dollar
    \\ backslash
    / slash
    / slash
    """
expect:
    multi == dollarSlashy


given: "A String with words we want to match"
    def quickBrownFox =
            "The quick brown fox jumps over the lazy dog."
and: "a matcher built via the find operator"
    def matcher = quickBrownFox =~ /\b.o.\b/
expect: "to match all three letter words with middle letter o"
    matcher.findAll() == ['fox','dog']


//precompiled matcher works faster
given: "A String with words we want to match"
    def quickBrownFox2 =
            "The quick brown fox jumps over the lazy dog."
and: "a matcher built via a pattern object"
    def pattern = ~/\b.o.\b/
    def matcher2 = pattern.matcher( quickBrownFox )
expect: "to match all three letter words with middle letter o"
    matcher.findAll() == ['fox','dog']

