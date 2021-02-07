import { Construct } from "constructs";
import {App, Stack, StepsProps} from "cdkactions";
import {
  createGradleLibraryPullRequestWorkflow,
  createGradleLibraryPrepareForReleaseWorkflow,
  createGradleLibraryReleaseWorkflow
} from "@pixeloutlaw/github-cdkactions";

export class MyStack extends Stack {
  constructor(scope: Construct, id: string) {
    super(scope, id);

    // define workflows here
    const runSpigotBuildToolsSteps: StepsProps[] = [{
      name: "Run Spigot Build Tools",
      run: "./gradlew runSpigotBuildTools"
    }];
    createGradleLibraryPullRequestWorkflow(this, runSpigotBuildToolsSteps);
    createGradleLibraryPrepareForReleaseWorkflow(this, runSpigotBuildToolsSteps);
    createGradleLibraryReleaseWorkflow(this);
  }
}

const app = new App();
new MyStack(app, 'cdk');
app.synth();
