indexes=$(seq $1)
for index in $indexes; do
  echo run $index
  java -jar target/hibernate*.jar | tee output.txt
  tail -1 output.txt >> times.txt
done
rm output.txt
