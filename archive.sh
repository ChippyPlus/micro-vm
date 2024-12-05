#!/bin/zsh


BRANCH=master # Default
BRANCH=register-snapshot-work


mkdir .sharing
rm -rf .sharing/archiveBuffer .sharing/archiveWikiBuffer

git clone --depth 1 https://github.com/ChippyPlus/micro-vm.wiki.git .sharing/archiveWikiBuffer
git clone -b $BRANCH --depth 1 https://github.com/ChippyPlus/micro-vm.git .sharing/archiveBuffer

rm -rf .sharing/archiveWikiBuffer/.git  .sharing/archiveBuffer/.git  .sharing/archiveBuffer/archive.sh  .sharing/archiveBuffer/gradle  .sharing/archiveBuffer/.idea  .sharing/archiveBuffer/debug
find .sharing/archiveBuffer > .sharing/archiveOutBuffer.txt
find .sharing/archiveWikiBuffer >> .sharing/archiveOutBuffer.txt

files=""

while IFS= read -r i; do
    i=${i%$'\n'}

    ext=""
    case "$i" in
        *.kt) ext="kotlin" ;;
        *.md) ext="markdown" ;;
        *.sh) ext="shell" ;;
        *.json) ext="json" ;;
        *.kar) ext="custom assembly" ;;
    esac

    echo "$i"
    if [[ -n "$ext" ]]; then
        files+=$'\nfilePath '$i$'\n```'$ext$'\n'$(cat "$i")$'\n-------------------------------\n```'
    fi
done <  .sharing/archiveOutBuffer.txt



echo "$files" > share.txt