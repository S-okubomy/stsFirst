@echo off

REM # クラスパス設定
SET CLASS_PASS=.;./src/main/webapp/WEB-INF/lib/*;./src/main/webapp/WEB-INF/classes

REM # 各設定値をコマンドプロンプトに表示
REM echo EXECUTE_BAT=%~0
REM echo EXECUTE_CLASS=%EXECUTE_CLASS%
REM echo CLASS_PASS=%CLASS_PASS%


echo ###########################
echo # Java実行
echo ###########################

echo ########################################
echo # 実行プログラム    : TestNGramOut.class
echo ########################################

REM # 実行するJavaClass指定
SET EXECUTE_CLASS=rcgaBatch1.batch.TestNGramOut
echo java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%
java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%

echo ########################################
echo # 実行プログラム    : testExe1.class
echo ########################################

SET EXECUTE_CLASS=rcgaBatch1.batch.testExe1
echo java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%
java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%

echo ########################################
echo # 実行プログラム    : MainGetJitsuDate.class
echo ########################################

SET EXECUTE_CLASS=rcgaBatch1.batch.MainGetJitsuDate
echo java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%
java -Dfile.encoding=UTF-8 -cp %CLASS_PASS% %EXECUTE_CLASS%



pause