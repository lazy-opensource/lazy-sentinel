#!/bin/bash

# Author: laizhiyuan
# Desc: This is a release b2b api project script
# Create on 2018-01-17

#If in this machine has the script execution, then this will not be able to perform

declare -i is_script_execution_conflict=`ps aux | grep "**api-oauth2-deploy.sh" \
| grep -v "grep" | wc -l`

if [ "${is_script_execution_conflict}" -gt 2 ];then

	echo "Error:I'm sorry, but the current system has ${is_script_execution} \
		 process in the execution of this script, you can't perform"
	echo "Now Process Total: ${is_script_execution_conflict}"
	echo 'Info:You can use the command `ps aux | grep "**api-oauth2-deploy.sh" \
		 | grep -v "grep" | wc -l | awk "{print $1}"` view'
	exit 0
fi
env_param=$1
pn_param=$2
function check_param(){
	test -z ${env_param} && echo "please input env param" && exit 1
	test -z ${pn_param} && echo "please input project name param" && exit 1
	env_arr=('dev' 'test' 'prod')
	pn_arr=('oauth2')
	check_env_res='1'
	check_pn_res='1'
	for i in ${env_arr[@]}
	do 
		test $i = ${env_param} && check_env_res='2'
	done
	test ${check_env_res} != "2" && echo "env param support: dev test prod" && exit 1

	for i in ${pn_arr[@]}
        do 
                test $i = ${pn_param} && check_pn_res="2"
        done
        test ${check_pn_res} != "2" && echo "project name param support: oauth2" && exit 1


}
check_param

# init var func
function init_var(){
	date=`date +%Y%m%d`
	maven_settings=/usr/local/etc/.settings-suguo.xml
	project_module=${pn_param}
	project_name=api-${project_module}
	parent_pom=${project_module}-parent
	git_url=https://laizhiyuan:lzy10086..@10.68.25.20/suguo/${project_name}.git
	branch=develop
	work_root_dir=/usr/local/suguo
	build_dir=${work_root_dir}/build
	backup_dir=${work_root_dir}/backup/${project_name}
	deploy_dir=${work_root_dir}/deploy/${project_name}
	jar_name=${project_name}.jar
	server_dir=/etc/init.d/${project_name}
	maven_repo_tcl_dir=/var/local/maven-repo/com/tcl
	build_jar=${build_dir}/${project_name}/${project_module}-web/target/${project_name}.jar
}
init_var

# init dir and file
function init_dir_and_file(){
	test ! -d "${work_root_dir}" && mkdir -p ${work_root_dir}
	test ! -d "${build_dir}" && mkdir -p ${build_dir}
	test ! -d "${backup_dir}" && mkdir -p ${backup_dir}
	test ! -d "${deploy_dir}" && mkdir -p ${deploy_dir}
}
init_dir_and_file

# exexution git pull or git clone 
function update_project(){
	cd ${build_dir}
	echo "current work dir is: $(pwd)" && sleep 2s

#	test -d "${build_dir}/${project_name}" && rm -rf "${build_dir}/${project_name}"
	if [ -d "${build_dir}/${project_name}" ];then
		cd ${build_dir}/${project_name}
		git checkout ${branch}
		test "$?" != 0 && echo "git checkout error" && exit 1
		echo "current branc $(git branch -a | grep '*')"
		git pull origin ${branch}
		test "$?" !=0 && echo "git pull error" && exit 1
		echo "git pull finish" && sleep 2s
	else
		git clone -b ${branch} ${git_url} 
		test "$?" != 0 && echo "git clone error" && exit 1
		echo "git clone finish" && sleep 2s
	fi

	echo "update project finish, current dir $(pwd)" && sleep 2s

}
update_project

# excute mvn clean package word
function build_project(){
	cd ${build_dir}/${project_name}/${parent_pom}
	rm -rf ${maven_repo_tcl_dir}
	mvn clean package --settings=${maven_settings} -Dmaven.test.skip=true -P ${env_param}

	#error_log_content=`cat ${error_log_filr} | head -n 1`
	[ "$?" != 0 ] && echo "maven build error..for details please check ${error_log_file} file" && exit 1
	
	echo "maven build finish..." && sleep 2s	
}
build_project

# use gzip compression algorithm backup
function backup(){
	cd ${deploy_dir}
	if [ -f "${deploy_dir}/${jar_name}" ];then
		tar -zcvf ${project_name}-${date}.tar.gz ${jar_name}
		test "$?" != 0 && echo "back error" && exit 1
        	mv ${project_name}-${date}.tar.gz ${backup_dir}/
        	test "$?" != 0 && echo "move back error" && exit 1
        	echo "backup finish..." && sleep 2s
	fi

}

backup

# deploy dir
function deploy(){
	test -f "${deploy_dir}/${jar_name}" && rm -rf ${deploy_dir}/${jar_name}
	cp -arvf ${build_jar} ${deploy_dir}/
	test "$?" != 0 && echo "copy build jar to deploy error" && exit 1
	test ! -f ${server_dir} && ln -s ${build_jar} ${server_dir}
	chmod 775 ${server_dir}
	service ${project_name} stop 2> /dev/null
	service ${project_name} start
}

deploy











